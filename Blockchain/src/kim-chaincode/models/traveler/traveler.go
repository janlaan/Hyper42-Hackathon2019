package traveler

import (
	"crypto/sha256"
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"strings"
)

type TravelerInformationPiece struct {
	Claim        string `json:"claim"`
	Response     string `json:"response"`
	RightToRead  string `json:"rightToRead"`
	WhereMayRead string `json:"whereMayRead"`
	WhoMayRead   string `json:"whoMayRead"`
}

type Bundle struct {
	Hash string `json:"hash"`
}

type Partial struct {
	Picture string `json:"picture"`
}

func RegisterClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 6
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}

	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}
	travelerInformationPiece := TravelerInformationPiece{
		Claim:        args[1],
		Response:     args[2],
		RightToRead:  args[3],
		WhereMayRead: args[4],
		WhoMayRead:   args[5],
	}

	travelerInformationPieceAsBytes, e := json.Marshal(travelerInformationPiece)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	APIstub.PutState(id, travelerInformationPieceAsBytes)
	return shim.Success([]byte(id))
}

func ChallengeClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 4
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}
	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})

	creator, creatorError := APIstub.GetCreator()
	if creatorError != nil {
		return shim.Error("Error extracting submitter information.")
	}

	travelerInformationPieceAsBytes, er := APIstub.GetState(id)
	var travelerInformationPiece TravelerInformationPiece
	err := json.Unmarshal(travelerInformationPieceAsBytes, &travelerInformationPiece)
	if e == nil && er == nil && err == nil &&
		travelerInformationPiece.Claim == args[1] &&
		strings.Contains(travelerInformationPiece.RightToRead, string(creator)) &&
		strings.Contains(travelerInformationPiece.WhereMayRead, args[3]) {
		return shim.Success([]byte(travelerInformationPiece.Response))

	}
	return shim.Error("Error, not found or insufficient rights")
}

func CheckHash(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 3
	if len(args) < amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting at least %d.", amountOfArguments))
	}
	idHash, e := APIstub.CreateCompositeKey("bundle", []string{args[0]})
	if e != nil {
		shim.Error("recreating hashIdfailed")
	}

	unHashed := args[1]

	for i := 2; i < len(args); i++ {
		if !isIdValid(APIstub, args[i]) {
			return shim.Error("Id is not valid")
		}
		unHashed = unHashed + args[i]
	}
	responseGetBundle, e := APIstub.GetState(idHash)
	if e != nil {
		shim.Error("retrieving hash failed")
	}

	var bundle Bundle

	err := json.Unmarshal(responseGetBundle, &bundle)
	if err != nil {
		shim.Error("unmarshal failed")
	}

	hash := sha256.Sum256([]byte(unHashed))

	ding := fmt.Sprintf("%x", hash)

	if ding == bundle.Hash {
		return shim.Success(nil)
	} else {
		return shim.Error("fail")
	}

}

func RegisterHash(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 2
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}
	id, e := APIstub.CreateCompositeKey("bundle", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}
	//TODO: create and store hash
	//insert Hash creation : this needs to be done
	bundle := Bundle{
		Hash: args[1],
	}
	bundleAsBytes, e := json.Marshal(bundle)

	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	e = APIstub.PutState(id, bundleAsBytes)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Putting new state failed")
	}

	return shim.Success([]byte(id))

}

func isIdValid(APIstub shim.ChaincodeStubInterface, id string) bool {
	response, e := APIstub.GetState(id)
	if response == nil || e != nil {
		return false
	}
	return true

}
