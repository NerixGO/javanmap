#!/bin/bash

version="v0.2.4"

for arg in "$@"; do
    if [[ -z $1 || $arg == "--help" || $arg == "-h" ]]; then

        echo "
    BEFORE USAGE MAKE SURE JAVA IS INSTALLED!

    Javanmap $version ( https://github.com/NerixGO/javanmap )
    Javanmap is a Nmap wroted from scratch with java language!
    Usage: javanmap {target specification}
    
    TARGET SPECIFICATION:
        Can pass IP addresses
        Ex: 192.168.0.1

    EXAMPLES:
        javanmap 192.168.0.1"

        exit 0
    fi
done

mkdir -p bin
if [[ -z "$(find bin -type f -name '*.class' 2>/dev/null)" ]]; then
    javac -d bin *.java
fi

echo "Javanmap $version ( https://github.com/NerixGO/javanmap )"

java -cp bin Main "$@"