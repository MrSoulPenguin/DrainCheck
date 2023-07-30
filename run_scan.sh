#!/bin/bash

# Check if the input directory argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <input_directory>"
    exit 1
fi

# Set the input directory from the command-line argument
source_directory="$1"

# Change the following line to the location of the scan.jar file
scan_jar="./DrainCheck-1.0.jar"

for jar_file in "$source_directory"/*.jar; do
    jar_file="$(basename "$jar_file")"
    output_file="${jar_file%.jar}"
    java -jar "$scan_jar" "$source_directory/$jar_file" "$source_directory/$output_file"
done

echo "All scans completed."
echo "The results can be found in $source_directory/results.txt"

read -rsp "Press any key to exit..."