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

	id := "fpfpf"
	salt := "asdfa"
	id1 := string(claim.GetPayload())
	fmt.Println(id1)
	//id2 := "1212"
	//id3 := "1212"
	//id4 := "1212"
	response := mockStub.MockInvoke("asdfasdf", [][]byte{[]byte("checkBundle"),
		[]byte(id),
		[]byte(salt),
		[]byte(id1),
		//[]byte(id2),
		//[]byte(id3),
		//[]byte(id4),
	})

	if shim.OK != response.Status {
	}
	fmt.Printf("error: %s", response.Message)
	t.Error("Invoke checkBundle failed")
	t.FailNow()
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

	return mockStub.MockInvoke("54534fdZ43ff", [][]byte{[]byte("registerTraveler"),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
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
	case "registerTraveler":
		return RegisterClaim(APIstub, args)

	default:
		return shim.Error("Error:teststup doesnt recognise " + function)
	}
}
