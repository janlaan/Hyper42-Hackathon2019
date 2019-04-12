package main

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	"testing"
)

func TestDigitalIdentity_Init(t *testing.T) {

	mockStub := createChainCode(t)
	response1 := mockStub.MockInit("001", [][]byte{[]byte("Init")})

	if response1.Status != 200 {
		t.Error("Basic method fail! Blocking issue!")
		t.FailNow()

	}

}

func TestSwitchFunction(t *testing.T) {
	mockStub := createChainCode(t)

	response1 := mockStub.MockInvoke("asd1", [][]byte{[]byte("registerClaim")})
	response2 := mockStub.MockInvoke("asd2", [][]byte{[]byte("challengeClaim")})
	response3 := mockStub.MockInvoke("asd3", [][]byte{[]byte("storeProfilePic")})
	response4 := mockStub.MockInvoke("asd4", [][]byte{[]byte("retrieveProfilePic")})
	response5 := mockStub.MockInvoke("asd5", [][]byte{[]byte("registerHash")})
	response6 := mockStub.MockInvoke("asd6", [][]byte{[]byte("checkHash")})
	response7 := mockStub.MockInvoke("asd7", [][]byte{[]byte("sdsdgfsdgf345")})

	if response1.Message != "Incorrect number of arguments. Expecting 6." {
		t.Error("unexpected error message on response1.")
		t.FailNow()
	}

	if response2.Message != "Incorrect number of arguments. Expecting 4." {
		t.Error("unexpected error message on response2.")
		t.FailNow()
	}

	if response3.Message != "Incorrect number of arguments. Expecting 2." {
		t.Error("unexpected error message on response3.")
		t.FailNow()
	}

	if response4.Message != "Incorrect number of arguments. Expecting at least 5." {
		fmt.Println(response4.Message)
		t.Error("unexpected error message on response4.")
		t.FailNow()
	}

	if response5.Message != "Incorrect number of arguments. Expecting 2." {
		fmt.Println(response5.Message)
		t.Error("unexpected error message on response5.")
		t.FailNow()
	}
	if response6.Message != "Incorrect number of arguments. Expecting at least 3." {
		t.Error("unexpected error message on response6.")
		t.FailNow()
	}
	if response7.Message != "Error: CTO-1: Invalid Smart Contract function name." {
		t.Error("unexpected error message on response7.")
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
