ECHO OFF
set JAVA_OPTS=
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.port=39999
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.ssl=false
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.authenticate=false
rem set JAVA_OPTS=%JAVA_OPTS% -Dhades.engine.config.file=config/LoginTest.hades.xml
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.host=localhost
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.port=33333
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.useauth=yes
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.username=mgmtuser

set CP=
set CP=%CP%;lib\HadesFIXA.jar
set CP=%CP%;lib\HadesFIXU.jar
set CP=%CP%;lib\appframework-1.0.3.jar
set CP=%CP%;lib\swing-worker-1.1.jar
set CP=%CP%;lib\jmxremote.jar
set CP=%CP%;lib\jmxremote_optional.jar
set CP=%CP%;lib\rmissl.jar

java %JAVA_OPTS% -cp %CP% com.marvisan.hades.fix.admin.cmdline.HadesAdminCmdLine