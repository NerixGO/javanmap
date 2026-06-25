#!/bin/bash

version="v0.2.4"

if [ -z "$(ls -A bin 2>/dev/null)" ]; then
    javac -d bin *.java
fi

if [[ -z $1 ]]; then

    echo "
    BEFORE USAGE MAKE SURE JAVA IS INSTALLED!
    
    Javanmap $version ( https://github.com/NerixGO/javanmap )
    Javanmap is a Nmap wroted from 0 with java language!
    Usage: javanmap {target specification}
    
    TARGET SPECIFICATION:
        Can pass IP addresses
        Ex: 192.168.1.1

    EXAMPLES:
        javanmap 192.168.1.1"

    exit
fi

ip="${@: -1}"
java -cp bin Main $version $ip