#!/bin/bash

echo
echo " ____    _____      _      ____    _____ "
echo "/ ___|  |_   _|    / \    |  _ \  |_   _|"
echo "\___ \    | |     / _ \   | |_) |   | |  "
echo " ___) |   | |    / ___ \  |  _ <    | |  "
echo "|____/    |_|   /_/   \_\ |_| \_\   |_|  "
echo
echo "Chaincode installation"
echo
CHANNEL_NAME="hyperchannel"
DELAY=3
LANGUAGE="golang"
TIMEOUT=10
VERBOSE="false"
LANGUAGE=`echo "$LANGUAGE" | tr [:upper:] [:lower:]`
COUNTER=1
MAX_RETRY=5
NAME=$1
VERSION=$2

CC_SRC_PATH="kim-chaincode/"

echo "Channel name : "$CHANNEL_NAME

# import utils
. scripts/utils.sh

## Install chaincode on peer0.org1 and peer0.org2
echo "Resolving dependencies..."
go get $GOPATH/src/kim-chaincode
echo "Installing chaincode on peer0.org1..."
installChaincode $NAME $VERSION 0 1
echo "Installing chaincode on peer1.org1..."
installChaincode $NAME $VERSION 1 1
echo "Installing chaincode on peer0.org2..."
installChaincode $NAME $VERSION 0 2
echo "Installing chaincode on peer1.org2..."
installChaincode $NAME $VERSION 1 2

echo "========= All GOOD, Chaincode install completed =========== "
echo

echo
echo " _____   _   _   ____   "
echo "| ____| | \ | | |  _ \  "
echo "|  _|   |  \| | | | | | "
echo "| |___  | |\  | | |_| | "
echo "|_____| |_| \_| |____/  "
echo

exit 0
