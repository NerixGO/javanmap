#!/bin/bash

if [[ -z $1 ]]; then

    echo "
    BEFORE USAGE MAKE SURE JAVA IS INSTALLED!
    
    Javanmap 0.1 ( https://github.com/NerixGO/javanmap )
    Javanmap is a Nmap wroted from 0 with java language!
    Usage: javanmap [Scan Type(s)] [Options] {target specification}
    
    TARGET SPECIFICATION:
        Can pass IP addresses
        Ex: 192.168.1.1

    EXAMPLES:
        javanmap 192.168.1.1"

    exit
fi

ip="${@: -1}"
java Main "$ip"