rem HadesFIX GUI admin utility
rem

@ECHO OFF

call env.cmd
set JAVA_OPTS=%JAVA_OPTS% -Dhades.admin.config.file=HadesAdminConsoleConfig.xml

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp %CP%  net.hades.fix.admin.gui.HadesAdminConsole