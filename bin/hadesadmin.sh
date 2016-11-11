# HadesFIX command line admin utility
#
. env.sh

JAVA_OPTS="$JAVA_OPTS -Dhadesfix.remotejmx.host=$1"
JAVA_OPTS="$JAVA_OPTS -Dhadesfix.remotejmx.port=$2"
JAVA_OPTS="$JAVA_OPTS -Dhadesfix.remotejmx.usessl=false"

echo $JAVA_OPTS

$JAVA_HOME/bin/java $JAVA_OPTS -cp $CP net.hades.fix.admin.cmdline.HadesAdminCmdLine