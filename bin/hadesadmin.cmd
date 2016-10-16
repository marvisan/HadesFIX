rem HadesFIX command line admin utility
rem

@ECHO OFF

call env.cmd
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.host=%1
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.port=%2
set JAVA_OPTS=%JAVA_OPTS% -Dhadesfix.remotejmx.usessl=false

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp %CP%  net.hades.fix.admin.cmdline.HadesAdminCmdLine