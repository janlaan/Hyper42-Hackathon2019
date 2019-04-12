package main

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"kim-chaincode/models/profilePic"
	"kim-chaincode/models/traveler"
)

type DigitalIdentity struct {
}

func (s *DigitalIdentity) Init(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (s *DigitalIdentity) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {
	function, args := APIstub.GetFunctionAndParameters()
	fmt.Printf("calling funciton %s in the main", function)

	switch function {
	case "registerClaim":
		return traveler.RegisterClaim(APIstub, args)

	case "challengeClaim":
		return traveler.ChallengeClaim(APIstub, args)

	case "storeProfilePic":
		return profilePic.StoreProfilePic(APIstub, args)

	case "retrieveProfilePic":
		return profilePic.RetrieveProfilePic(APIstub, args)

	case "registerHash":
		return traveler.RegisterHash(APIstub, args)

	case "checkHash":
		return traveler.CheckHash(APIstub, args)

	default:
		return shim.Error("Error: CTO-1: Invalid Smart Contract function name.")
	}

}

func main() {

	err := shim.Start(new(DigitalIdentity))
	if err != nil {
		fmt.Printf("Error creating new SmartContract: %s", err)
	}
}
