# echo "关闭所有进程"
# ps -ef|grep triumph-service|grep -v grep|cut -c 9-15|xargs --no-run-if-empty kill -9
project=/usr/triumph
echo  $project
cd $project
echo "开始构建项目"
mvn clean install -Dmaven.test.skip=true  -P dev


echo "开始构建eureka服务"
cd $project
cd registry
ps -ef | grep triumph-service-eureka | grep -v grep | awk '{print $2}'|xargs --no-run-if-empty kill -9
cd target
nohup java -Xms128m -Xmx128m -jar triumph-service-eureka-0.0.1-SNAPSHOT.jar  >/home/logs/triumph-service-eureka.log&
echo "开始构建zuul服务"
cd $project
cd gateway
ps -ef | grep triumph-service-zuul | grep -v grep | awk '{print $2}'|xargs --no-run-if-empty kill -9
cd target
nohup java -Xms128m -Xmx128m -jar triumph-service-zuul-0.0.1-SNAPSHOT.jar >/home/logs/triumph-service-zuul.log&
echo "开始构建business服务"
cd $project
cd service
cd business
ps -ef | grep triumph-service-business | grep -v grep | awk '{print $2}'|xargs --no-run-if-empty kill -9
cd target
nohup java -Xms256m -Xmx256m -jar triumph-service-business-0.0.1-SNAPSHOT.jar >/home/logs/triumph-service-business.log&
echo "开始构建file服务"
cd $project
cd service
cd file
ps -ef | grep xc-service-file | grep -v grep | awk '{print $2}'|xargs --no-run-if-empty kill -9
cd target
nohup java -Xms256m -Xmx256m -jar xc-service-file-1.0-SNAPSHOT.jar >/home/logs/xc-service-file.log&
