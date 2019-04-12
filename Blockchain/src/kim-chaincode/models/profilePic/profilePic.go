package profilePic

import (
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"kim-chaincode/models/traveler"
)

type ProfilePic struct {
	PartialPic string `json:"partialPic"`
}

func StoreProfilePic(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 2
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}

	id, e := APIstub.CreateCompositeKey("pic", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating key failed")
	}
	profilepic := ProfilePic{
		PartialPic: args[1],
	}
	picAsBytes, e := json.Marshal(profilepic)

	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Creating json failed")
	}

	e = APIstub.PutState(id, picAsBytes)
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Putting new state of the profilePic failed")
	}

	return shim.Success([]byte(id))

}

func RetrieveProfilePic(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 5
	if len(args) < amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting at least %d.", amountOfArguments))
	}

	id, e := APIstub.CreateCompositeKey("pic", []string{args[0]})
	if e != nil {
		fmt.Println(e.Error())
		return shim.Error("Recreating key failed")
	}

	responseOfHashCheck := traveler.CheckHash(APIstub, args)

	if shim.OK == responseOfHashCheck.Status {

		responseOfProfilePic, e := APIstub.GetState(id)

		if e != nil {
			return shim.Error("Retrieving state of the profilePic failed")
		}
		return shim.Success(responseOfProfilePic)
	}
	return shim.Error(responseOfHashCheck.Message)
}
