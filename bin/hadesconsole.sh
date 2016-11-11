# HadesFIX GUI admin utility
#
. env.sh


JAVA_OPTS="$JAVA_OPTS -Dhades.admin.config.file=HadesAdminConsoleConfig.xml"

echo $JAVA_OPTS

$JAVA_HOME/bin/java $JAVA_OPTS -cp $CP net.hades.fix.admin.gui.HadesAdminConsole