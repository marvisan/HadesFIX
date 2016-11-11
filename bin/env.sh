#
# Set it to your HadesFIX engine installation directory
#
HADES_HOME=/opt/HadesFIX

JAVA_HOME=/usr/java/jdk1.6.0_32

JAVA_OPTS="-Xms64M -Xmx256M -Dswing.aatext=true -Dawt.useSystemAAFontSettings=lcd -Djava.endorsed.dirs=$HADES_HOME/lib/endorsed"

cygwin=false
darwin=false
case "`uname`" in
	CYGWIN*) cygwin=true;;
	Darwin*) darwin=true;;
esac

if [ -z "$JAVA_HOME" ]; then
	echo JAVA_HOME must be set to the local JAVA installation directory
	exit 1
fi

if [ -z "$HADES_HOME" ]; then
	echo HADES_HOME must be set to the local HadesFIX installation directory
	exit 1
fi

if $cygwin; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
  [ -n "$CP" ] && CP=`cygpath --path --unix "$CP"`
fi

CP=$CP:$HADES_HOME/lib/HadesFIXE.jar
CP=$CP:$HADES_HOME/lib/HadesFIXM.jar
CP=$CP:$HADES_HOME/lib/HadesFIXA.jar
CP=$CP:$HADES_HOME/lib/HadesFIXU.jar
CP=$CP:$HADES_HOME/lib/HadesFIXT.jar
CP=$CP:$HADES_HOME/lib/activation.jar
CP=$CP:$HADES_HOME/lib/jmxremote.jar
CP=$CP:$HADES_HOME/lib/jmxremote_optional.jar
CP=$CP:$HADES_HOME/lib/rmissl.jar
CP=$CP:$HADES_HOME/lib/beansbinding-1.2.1.jar

if $cygwin; then
  JAVA_HOME=`cygpath --absolute --windows "$JAVA_HOME"`
  CP=`cygpath --path --windows "$CP"`
fi

export CP
