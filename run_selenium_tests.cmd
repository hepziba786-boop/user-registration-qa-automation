@echo off
REM ============================================================
REM  run_selenium_tests.cmd
REM  Builds and runs the Selenium test suite for the
REM  User Registration automation project using Maven.
REM
REM  Usage (from command line or Eclipse External Tools):
REM    1. Open Eclipse -> Run -> External Tools -> External Tools Configurations
REM    2. Create a new "Program" configuration
REM    3. Set Location to the full path of this .cmd file
REM    4. Set Working Directory to the project root (where this file lives)
REM    5. Click Run
REM ============================================================

REM Change to the Maven project directory
cd /d "%~dp0user-registration-automation"

REM Run all Selenium tests via Maven
mvn clean test

REM Pause so you can read the output before the window closes
pause
