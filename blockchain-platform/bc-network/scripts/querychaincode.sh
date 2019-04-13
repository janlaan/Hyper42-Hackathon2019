#!/bin/bash
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


# import utils
. scripts/utils.sh

# Invoke chaincode on peer1.org1
chaincodeQuery $@
exit 0
