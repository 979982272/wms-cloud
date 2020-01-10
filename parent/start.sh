cd /usr/local/cloud-server/

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./eureka-server/lib -jar  ./eureka-server/server-0.0.1-SNAPSHOT-classes.jar   >/dev/null 2>&1 &
9090

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./eureka-config/lib -jar ./eureka-config/config-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
9096

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./eureka-zipkin/lib  -jar ./eureka-zipkin/zipkin-0.0.1-SNAPSHOT.jar --spring.cloud.inetutils.ignoredInterfaces=.*>/dev/null 2>&1 &
9097

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./eureka-zuul/lib  -jar ./eureka-zuul/zuul-0.0.1-SNAPSHOT.jar --spring.cloud.inetutils.ignoredInterfaces=.*>/dev/null 2>&1 &
8085

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -Dloader.path=./basic-client/lib  -jar ./basic-client/basic-client-0.0.1-SNAPSHOT-classes.jar --spring.cloud.inetutils.ignoredInterfaces=.*>/dev/null 2>&1 &
8081

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java  -Dloader.path=./crm-client/lib  -jar ./crm-client/crm-client-0.0.1-SNAPSHOT-classes.jar --spring.cloud.inetutils.ignoredInterfaces=.* >/dev/null 2>&1 &
8082

nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java -server -Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M -Dloader.path=./eureka-admin/lib -jar  ./eureka-admin/admin-0.0.1-SNAPSHOT-classes.jar  --spring.cloud.inetutils.ignoredInterfaces=.* >/dev/null 2>&1 &
8088/8080


nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java  -Dloader.path=./dispatch-client/lib  -jar ./dispatch-client/dispatch-client-0.0.1-SNAPSHOT-classes.jar --spring.cloud.inetutils.ignoredInterfaces=.* >/dev/null 2>&1 &
阿里
nohup /usr/lib/jvm/java-1.8.0-openjdk/bin/java  -Dloader.path=./dispatch-client/lib  -jar ./dispatch-client/dispatch-client-0.0.1-SNAPSHOT.jar --spring.cloud.inetutils.ignoredInterfaces=.* >/dev/null 2>&1 &