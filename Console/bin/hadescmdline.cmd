set JAVA_OPTS=
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.port=39999
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.ssl=false
rem set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.authenticate=false
rem set JAVA_OPTS=%JAVA_OPTS% -Dhades.engine.config.file=config/LoginTest.hades.xml
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.useauth=yes
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.username=user1
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.password=user1_password

set CP=
set CP=%CP%;lib\HadesFIXE.jar
set CP=%CP%;lib\HadesFIXM.jar
set CP=%CP%;lib\activation.jar
set CP=%CP%;lib\jaxb-api.jar
set CP=%CP%;lib\jaxb-impl.jar
set CP=%CP%;lib\jaxb-xjc.jar
set CP=%CP%;lib\jaxb1-impl.jar
set CP=%CP%;lib\jsr173_api.jar

java %JAVA_OPTS% -cp %CP% com.marvisan.hades.fix.engine.HadesInstance