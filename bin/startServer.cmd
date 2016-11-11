rem
rem Set it to your HadesFIX engine instance configuration file
rem
set HADES_CONFIG_FILE=SampleServer.hades.xml

call env.cmd
set JAVA_OPTS=%JAVA_OPTS% -Dhades.engine.config.file=%HADES_CONFIG_FILE%

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp %CP% net.hades.fix.engine.HadesInstance