package profilePic

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
)

type ProfilePic struct {
	PartialPic string `json:"partialPic"`
}

func StoreProfilePic(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 2
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}
	return shim.Success(nil)
}

func RetrieveProfilePic(APIstub shim.ChaincodeStubInterface, args []string) sc.Response {
	amountOfArguments := 2
	if len(args) != amountOfArguments {
		return shim.Error(fmt.Sprintf("Incorrect number of arguments. Expecting %d.", amountOfArguments))
	}
	return shim.Success(nil)
}
