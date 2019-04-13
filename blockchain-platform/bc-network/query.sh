#!/bin/bash

string=$(curl -X GET http://localhost:5984/_all_dbs | sed 's/\[//' | sed 's/\]//' | sed 's/\"//g')

IFS=', ' read -a array <<< "$string"

for database in "${array[@]}"
do
    $(curl -X GET http://localhost:5984/$database/_all_docs?include_docs=true >> allData.txt)
done
