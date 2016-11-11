@ECHO OFF

set CP=
set CP=%CP%;lib\HadesFIXU.jar

rem -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=29999
java -cp %CP% com.marvisan.hades.fix.commons.security.PasswordBank