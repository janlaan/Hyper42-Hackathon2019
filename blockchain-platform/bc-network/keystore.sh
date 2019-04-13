#!/bin/bash

echo "***************************"
echo "* Start creating keystore *"
echo "***************************"

find . -path "**/tlsca/**" -name "*.pem" |while read fname; do
  echo "$fname"
  ALIAS="${fname##*/}"
  echo "$ALIAS"
  keytool -importcert -alias $ALIAS -file $fname -keystore crypto-config/hyper42.jks -storepass changeit -noprompt 
done

