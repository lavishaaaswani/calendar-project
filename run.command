#!/bin/bash
cd "$(dirname "$0")"
java -cp "lib/*:." test
read -n 1 -s -r -p "Press any key to close this window"
