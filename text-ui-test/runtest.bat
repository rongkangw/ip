@ECHO OFF

pushd "%~dp0\.."

REM create bin directory if it doesn't exist
if not exist bin mkdir bin

REM delete output from previous run
if exist text-main-test\ACTUAL.txt del text-main-test\ACTUAL.txt

REM delete TaskHistory.txt from previous run
if exist src\main\data\TaskHistory.txt del src\main\data\TaskHistory.txt

REM compile the code into the bin folder
javac -Xlint:none -d bin ^
src\main\java\exceptions\*.java ^
src\main\java\feature\*.java ^
src\main\java\feature\tasks\*.java ^
src\main\java\main\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath bin TheCoolerDuke < text-main-test\INPUT.txt > text-main-test\ACTUAL.txt

REM compare output to expected
FC text-main-test\ACTUAL.txt text-main-test\EXPECTED.txt
