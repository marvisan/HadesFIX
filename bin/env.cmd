@echo off

rem
rem Set it to your HadesFIX engine iinstallation directory
rem
set HADES_HOME=C:\HadesFIX
rem set JAVA_HOME=C:\Apps\Java

set JAVA_OPTS=-Xms64M -Xmx256M -Djava.endorsed.dirs=%HADES_HOME%\lib\endorsed

if "%JAVA_HOME%" == "" goto gotoNoJavaHome
if "%HADES_HOME%" == "" goto gotoNoHadesHome

set CP=
set CP=%CP%;%HADES_HOME%\lib\HadesFIXE.jar
set CP=%CP%;%HADES_HOME%\lib\HadesFIXM.jar
set CP=%CP%;%HADES_HOME%\lib\HadesFIXA.jar
set CP=%CP%;%HADES_HOME%\lib\HadesFIXU.jar
set CP=%CP%;%HADES_HOME%\lib\HadesFIXT.jar
set CP=%CP%;%HADES_HOME%\lib\activation.jar
set CP=%CP%;%HADES_HOME%\lib\jmxremote.jar
set CP=%CP%;%HADES_HOME%\lib\jmxremote_optional.jar
set CP=%CP%;%HADES_HOME%\lib\rmissl.jar
set CP=%CP%;%HADES_HOME%\lib\beansbinding-1.2.1.jar
goto endScript

:gotoNoJavaHome
echo JAVA_HOME must be set to the local JAVA installation directory

:gotoNoHadesHome
echo HADES_HOME must be set to the local HadesFIX installation directory

:endScript
%JAVA_HOME%\bin\java -version
@echo on