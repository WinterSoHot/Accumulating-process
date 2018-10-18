#!/bin/bash

for file in `ls /etc`;do
	echo file
done

echo "------------"

for file in $(ls /etc);do
	echo "2 $file"
done

for skill in Ada Coffe Action Java;do
	echo "I am good at ${skill}Script"
done
