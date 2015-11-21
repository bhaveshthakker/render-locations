#!/bin/bash


#echo 'root:komli123' | chpasswd

echo "Preparing for deploying..."
cd /tmp/docker_work/
mkdir app-resources-processed
cp -rf ${APP_RESOURCES_ARTIFACT}/* app-resources-processed/

echo $ENVIORNMENT

#if [ "$APP_RESOURCES_PASSED" -ne "false" ]
# then  
#  if [ $ENVIORNMENT -ne "Nothing" ]
#   then
#    cp /tmp/docker_work/${APP_RESOURCES_ARTIFACT}/app-resources-$ENVIORNMENT.yml /app-resources.yml
#    echo "Using the values from enviornmet specific yml file"
#   else 
#    cp  /tmp/docker_work/${APP_RESOURCES_ARTIFACT}/app-resources.yml /
#    echo "Using the default values"
# else
#   echo "Used passed resources through -v options"
#fi


if [ $ENVIORNMENT != "Nothing" ]
  then    
     cp /tmp/docker_work/${APP_RESOURCES_ARTIFACT}/app-resources-$ENVIORNMENT.yml /app-resources.yml
     echo "Using the app resources values from enviornmet specific yml file : " 
     echo app-resources-$ENVIORNMENT.yml 
  else
     echo "Using the app resources values from the volume given in docker run." 
fi

sh /propertyReplace.sh /app-resources.yml /tmp/docker_work/app-resources-processed

echo "Using below app-resources properties"
cat /app-resources.yml

cp -rf /tmp/docker_work/app-resources-processed/* $CATALINA_HOME/lib/

ls $CATALINA_HOME/lib/


echo "Starting Tomcat ... "
sh $CATALINA_HOME/bin/startup.sh

echo "Catalina.out"
tail -f $CATALINA_HOME/logs/catalina.out


#/etc/init.d/sshd start

#exec /usr/sbin/sshd -D

