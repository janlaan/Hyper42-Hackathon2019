package traveler

import (
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

func RegisterTraveler(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
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
	}

	travelerInformationPieceAsBytes, e := json.Marshal(travelerInformationPiece)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	APIstub.PutState(id, travelerInformationPieceAsBytes)

	return shim.Success(nil)
}

func ChallengeClaim(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {

	id, e := APIstub.CreateCompositeKey("claim", []string{args[0]})

	travelerInformationPieceAsBytes, er := APIstub.GetState(id)
	var travelerInformationPiece TravelerInformationPiece
	err := json.Unmarshal(travelerInformationPieceAsBytes, &travelerInformationPiece)
	if e == nil &&
		er == nil &&
		err == nil &&
		travelerInformationPiece.Claim == args[1] &&
		strings.Contains(travelerInformationPiece.RightToRead, args[2]) &&
		strings.Contains(travelerInformationPiece.WhereMayRead, args[3]) {
		return shim.Success([]byte(travelerInformationPiece.Response))

	}
	return shim.Error("Error, not found or insufficient rights")
}
