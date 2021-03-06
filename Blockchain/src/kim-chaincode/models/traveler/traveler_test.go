package traveler

import (
	"encoding/json"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	sc "github.com/hyperledger/fabric/protos/peer"
	"testing"
)

type ProfilePic struct {
	PartialPic string `json:"partialPic"`
}

func TestRegisterHash(t *testing.T) {
	id1 := "56423123"
	id2 := "sd456sdf"
	id3 := "dfg45dr6"
	idPicture := "89bhjbhj"
	salt := "5465123sdf23"
	idBundle := "sdfsdf"
	mockStub := createChainCode(t)
	mockStub.MockInvoke("asdfasdf534wedf", [][]byte{[]byte("registerClaim"),
		[]byte(id1),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})

	mockStub.MockInvoke("54534fdZ43ff", [][]byte{[]byte("registerClaim"),
		[]byte(id2),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})
	mockStub.MockInvoke("kjhsdf89234", [][]byte{[]byte("registerClaim"),
		[]byte(id3),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})

	idPictureCreated, _ := mockStub.CreateCompositeKey("pic", []string{idPicture})
	profilepic := ProfilePic{
		PartialPic: "adsasd234dfasdsdf232334",
	}
	picAsBytes, _ := json.Marshal(profilepic)

	mockStub.MockTransactionStart(idPictureCreated)
	e := mockStub.PutState(idPictureCreated, picAsBytes)
	mockStub.MockTransactionEnd(idPictureCreated)

	if e != nil {
		fmt.Println(e.Error())
	}

	response1 := mockStub.MockInvoke("sfsdf23443", [][]byte{[]byte("registerHash"),
		[]byte(idBundle),
		[]byte(salt),
		[]byte(idPicture),
		[]byte(id1),
		[]byte(id2),
		[]byte(id3),
	})

	fmt.Println(response1.Status)
	fmt.Println(response1.Message)

}

func TestCheckHash(t *testing.T) {
	id1 := "56423123"
	id2 := "sd456sdf"
	id3 := "dfg45dr6"
	idPicture := "89bhjbhj"
	salt := "5465123sdf23"
	idBundle := "sdfsdf"
	mockStub := createChainCode(t)
	mockStub.MockInvoke("asdfasdf534wedf", [][]byte{[]byte("registerClaim"),
		[]byte(id1),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})

	mockStub.MockInvoke("54534fdZ43ff", [][]byte{[]byte("registerClaim"),
		[]byte(id2),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})
	mockStub.MockInvoke("kjhsdf89234", [][]byte{[]byte("registerClaim"),
		[]byte(id3),
		[]byte("Is allowed to buy alcohol"),
		[]byte("Yes"),
		[]byte("shops"),
		[]byte("schiphol"),
		[]byte("all"),
	})

	idPictureCreated, _ := mockStub.CreateCompositeKey("pic", []string{idPicture})
	profilepic := ProfilePic{
		PartialPic: "adsasd234dfasdsdf232334",
	}
	picAsBytes, _ := json.Marshal(profilepic)

	mockStub.MockTransactionStart(idPictureCreated)
	e := mockStub.PutState(idPictureCreated, picAsBytes)
	mockStub.MockTransactionEnd(idPictureCreated)

	if e != nil {
		fmt.Println(e.Error())
	}

	response1 := mockStub.MockInvoke("sfsdf23443", [][]byte{[]byte("registerHash"),
		[]byte(idBundle),
		[]byte(salt),
		[]byte(idPicture),
		[]byte(id1),
		[]byte(id2),
		[]byte(id3),
	})

	fmt.Println(response1.Status)
	fmt.Println(response1.Message)

	responseCheck := mockStub.MockInvoke("asdfasdf", [][]byte{[]byte("checkBundle"),
		[]byte(idBundle),
		[]byte(salt),
		[]byte(idPicture),
		[]byte(id1),
		[]byte(id2),
		[]byte(id3),
	})

	if shim.OK != responseCheck.Status {

		fmt.Printf("error: %s", responseCheck.Message)
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
