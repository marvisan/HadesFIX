rem Password Bank utility
rem

@ECHO OFF

call env.cmd

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp %CP%  net.hades.fix.commons.security.PasswordBank