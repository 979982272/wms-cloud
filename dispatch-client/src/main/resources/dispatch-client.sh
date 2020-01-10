#!/bin/sh

## java env
export JRE_HOME=/usr/lib/jvm/java-1.8.0-openjdk

## service name
APP_NAME=dispatch-client-0.0.1-SNAPSHOT-classes
JAR_FOLDER=dispatch-client
SERVICE_DIR=/usr/local/cloud-server/
JAR_NAME=$APP_NAME\.jar
PID=$APP_NAME\.pid
log=/usr/local/cloud-server/logs/

cd $SERVICE_DIR

case "$1" in

    start)
        nohup $JRE_HOME/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./$JAR_FOLDER/lib -jar ./$JAR_FOLDER/$JAR_NAME >/dev/null 2>&1 &
        echo $! > $SERVICE_DIR/$PID
        echo "=== start $APP_NAME"
        ;;

    stop)
        kill `cat $SERVICE_DIR/$PID`
        rm -rf $SERVICE_DIR/$PID
        echo "=== stop $APP_NAME"

        sleep 5
        P_ID=`ps -ef | grep -w "$APP_NAME" | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "=== $APP_NAME process not exists or stop success"
        else
            echo "=== $APP_NAME process pid is:$P_ID"
            echo "=== begin kill $APP_NAME process, pid is:$P_ID"
            kill -9 $P_ID
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $SERVICE_NAME"
        ;;
	log)
       tail -f $log/dispatch-client.log
        ;;
    *)
        ## restart
        nohup $JRE_HOME/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./$JAR_FOLDER/lib -jar ./$JAR_FOLDER/$JAR_NAME >/dev/null 2>&1 &
        echo $! > $SERVICE_DIR/$PID
        echo "=== start $APP_NAME"
        ;;

esac
exit 0

