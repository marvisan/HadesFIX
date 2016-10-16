#!/bin/sh
#
# Set it to your HadesFIX engine instance configuration file
#

. ./env.sh

HADES_CONFIG_FILE=SampleServer.hades.xml

if $cygwin; then
  HADES_CONFIG_FILE=`cygpath --absolute --windows "$HADES_CONFIG_FILE"`
fi

JAVA_OPTS="$JAVA_OPTS -Dhades.engine.config.file=$HADES_CONFIG_FILE"

$JAVA_HOME/bin/java $JAVA_OPTS -cp $CP net.hades.fix.engine.HadesInstance
