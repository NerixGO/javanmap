#!/bin/bash

version="v0.3.1"

for arg in "$@"; do
    if [[ $arg == "--help" || $arg == "-h" ]]; then

        printf "\n\033[1m   ---BEFORE USAGE MAKE SURE JAVA IS INSTALLED!---\033[0m\n"

    echo "
    Javanmap $version ( https://github.com/NerixGO/javanmap )
    Javanmap is a Nmap wroted from scratch with java language!
    Usage: javanmap [option] <target>
    
    TARGET SPECIFICATION:
        Can pass IP addresses
        Ex: 192.168.0.1

    EXAMPLES:
        javanmap 192.168.0.1
        
    SCAN OPTIONS:
        -p <port>, --port <port>                Use specific port. If port not specified, scan 1000 Most common used ports"

        exit 0
    fi
done

mkdir -p bin
if [[ -z "$(find bin -type f -name '*.class' 2>/dev/null)" ]]; then
    javac -d bin *.java
fi

echo "Javanmap $version ( https://github.com/NerixGO/javanmap )"

java -cp bin Main "$@"