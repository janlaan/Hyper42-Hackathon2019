package traveler

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"testing"
)

func TestCheckHash(t *testing.T) {
	mockStub := createChainCode(t)
	claim := createTraveler(*mockStub)

	idBasicForBubdle := "kandId"

	response1 := mockStub.MockInvoke("j32njkkjn", [][]byte{[]byte("registerHash"),
		[]byte(idBasicForBubdle),
		[]byte("021803d6092b9bb785983761522a47ca78cf1f36105a28518dca000d15a51d1e"),
	})

	//response1 := mockStub.MockInvoke("j32njkkjn", [][]byte{[]byte("registerHash"),
	//	[]byte("91d929cbd4386538794e77b5c4857a07d9811b475d05dba065a5c808ec57eeea"),
	//})
	if shim.OK != response1.Status {
		fmt.Printf("error: %s", response1.Message)
		t.Error("Invoke store hash failed")
	}

	salt := "asdfa"
	id1 := string(claim.GetPayload())
	fmt.Println("dit is de id  " + id1)
	response := mockStub.MockInvoke("asdfasdf", [][]byte{[]byte("checkBundle"),
		[]byte(idBasicForBubdle),
		[]byte(salt),
		[]byte(id1),
	})

	if shim.OK != response.Status {

		fmt.Printf("error: %s", response.Message)
		t.Error("Invoke checkBundle failed")
		t.FailNow()
	}
}

func createChainCode(t *testing.T) *shim.MockStub {
	sm := new(DigitalIdentity)
	mockStub := shim.NewMockStub("mockStub", sm)
	if mockStub == nil {
		t.Error("Cannot create MockStub")
		t.FailNow()
	}
	return mockStub
}

func createTraveler(mockStub shim.MockStub) sc.Response {

	return mockStub.MockInvoke("54534fdZ43ff", [][]byte{[]byte("registerClaim"),
		[]byte("892jkkjj"),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})

}

type DigitalIdentity struct {
}

// Initialisation of the chain
func (s *DigitalIdentity) Init(APIstub shim.ChaincodeStubInterface) sc.Response {
	return shim.Success(nil)
}

func (s *DigitalIdentity) Invoke(APIstub shim.ChaincodeStubInterface) sc.Response {
	function, args := APIstub.GetFunctionAndParameters()

	switch function {
	case "checkBundle":
		return CheckHash(APIstub, args)
	case "registerClaim":
		return RegisterClaim(APIstub, args)
	case "registerHash":
		return RegisterHash(APIstub, args)

	default:
		return shim.Error("Error:teststup does not recognise " + function)
	}
}
