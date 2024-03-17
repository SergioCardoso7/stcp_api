@ECHO OFF

REM Check if JAVA_HOME is set
IF EXIST "%JAVA_HOME%" (
  GOTO JavaHomeSet
) ELSE (
  ECHO Error: JAVA_HOME environment variable is not set.
  ECHO Please set JAVA_HOME to point to your JDK installation directory.
  EXIT /B 1
)

:JavaHomeSet

REM Check if MAVEN_HOME is set
IF EXIST "%MAVEN_HOME%" (
  GOTO MavenHomeSet
) ELSE (
  ECHO Error: MAVEN_HOME environment variable is not set.
  ECHO Please set MAVEN_HOME to point to your Maven installation directory.
  EXIT /B 1
)

:MavenHomeSet

REM Execute Maven commands with desired lifecycle phase (e.g., package, clean, etc.)
mvn %1 dependency:copy-dependencies package

IF %ERRORLEVEL% NEQ 0 (
  ECHO Error: Maven build failed. Please check the output for details.
  EXIT /B 1
)

ECHO Maven build completed successfully!