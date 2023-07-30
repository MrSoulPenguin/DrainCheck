@echo off
setlocal enabledelayedexpansion

REM Check if the input directory argument is provided
if "%~1"=="" (
    echo Usage: %0 ^<input_directory^>
    exit /b 1
)

REM Set the input directory from the command-line argument
set "source_directory=%~1"

REM Change the following line to the location of the scan.jar file
set "scan_jar=./DrainCheck-1.1.jar"

for %%I in ("%source_directory%\*.jar") do (
    set "jar_file=%%~nxI"
    set "output_file=!jar_file:.jar=!"
    java -jar "%scan_jar%" "%%~fI" "%source_directory%\!output_file!"
)

echo All scans completed.
echo The results can be found in %source_directory%\results.txt
pause
