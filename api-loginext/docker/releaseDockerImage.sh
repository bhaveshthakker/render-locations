#You need to be in dokcer directory to execute this script
#cd ${WORKSPACE}
#cd ../


#Login to the docker remote repository
sudo docker login -u PubMaticDockerAdmin -p PmD0cK3r -e manoj.danane@pubmatic.com  docker.pubmatic.com

echo $GROUP_ID : $ARTIFACT_ID : $ARTIFACT_VERSION : $FINAL_ARTIFACT_NAME : $ARTIFACT_PACKAGING_TYPE ;

#Copy the template docker file to actual docker file which will be used for current build.
rm -rf Dockerfile
cp Dockerfile.template Dockerfile

GROUP_ID=`echo $GROUP_ID | sed "s/\./\//g"`


#Replace the place holders with actual values of the current build.
sed "s|GROUP_ID_VAR|$GROUP_ID|g;s|ARTIFACT_ID_VAR|$ARTIFACT_ID|g;s|ARTIFACT_VERSION_VAR|$ARTIFACT_VERSION|g;s|FINAL_ARTIFACT_NAME_VAR|$FINAL_ARTIFACT_NAME|g;s|ARTIFACT_PACKAGING_TYPE_VAR|$ARTIFACT_PACKAGING_TYPE|g;s|APP_RESOURCES_ARTIFACT_VAR|$APP_RESOURCES_ARTIFACT_ID|g"  -i Dockerfile ;

#Build a docker image.
sudo docker build  --no-cache=true --tag=docker.pubmatic.com/$ARTIFACT_ID:$ARTIFACT_VERSION .

# Pushing the image to docker repo
sudo docker push docker.pubmatic.com/$ARTIFACT_ID:$ARTIFACT_VERSION
