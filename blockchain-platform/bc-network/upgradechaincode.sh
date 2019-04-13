#!/bin/bash
export PATH=${PWD}/../bin:${PWD}:$PATH
export FABRIC_CFG_PATH=${PWD}
export VERBOSE=false

# Print the usage message
function printHelp() {
  echo "Usage: "
  echo "  upgradechaincode.sh <name> <version>"
  echo "  upgradechaincode.sh -h (print this message)"
  echo
}


# Obtain the OS and Architecture string that will be used to select the correct
# native binaries for your platform, e.g., darwin-amd64 or linux-amd64
OS_ARCH=$(echo "$(uname -s | tr '[:upper:]' '[:lower:]' | sed 's/mingw64_nt.*/windows/')-$(uname -m | sed 's/x86_64/amd64/g')" | awk '{print tolower($0)}')
# timeout duration - the duration the CLI should wait for a response from
# another container before giving up
CLI_TIMEOUT=10
# default for delay between commands
CLI_DELAY=3
# channel name defaults to "mychannel"
CHANNEL_NAME="hyperchannel"
# use this as the default docker-compose yaml definition
COMPOSE_FILE=docker-compose-kafka.yaml
#
COMPOSE_FILE_COUCH=docker-compose-couch.yaml
# org3 docker compose file
COMPOSE_FILE_ORG3=docker-compose-org3.yaml
#
# use golang as the default language for chaincode
LANGUAGE=golang
# default image tag
IMAGETAG="latest"
# Parse commandline args

if test "$#" -ne 2; then
    echo "Unexpected amount of parameters"
    printHelp
    exit 1
else
  NAME=$1
  VERSION=$2
fi


docker exec cli scripts/upgradechaincode.sh $NAME $VERSION
if [ $? -ne 0 ]; then
  echo "ERROR, chaincode upgrade failed"
  exit 1
fi



