@ECHO OFF

pushd "%~dp0\.."

REM create bin directory if it doesn't exist
if not exist bin mkdir bin

REM delete output from previous run
if exist text-ui-test\ACTUAL.txt del text-ui-test\ACTUAL.txt

REM delete TaskHistory.txt from previous run
if exist src\main\data\TaskHistory.txt del src\main\data\TaskHistory.txt

REM compile the code into the bin folder
javac -Xlint:none -d bin ^
src\main\java\exceptions\*.java ^
src\main\java\taskFeature\*.java ^
src\main\java\taskFeature\tasks\*.java ^
src\main\java\ui\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath bin TheCoolerDuke < text-ui-test\INPUT.txt > text-ui-test\ACTUAL.txt

REM compare output to expected
FC text-ui-test\ACTUAL.txt text-ui-test\EXPECTED.txt
