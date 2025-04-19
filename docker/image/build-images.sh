#!/bin/bash
set -e

if ! docker info > /dev/null 2>&1; then
  echo "This script uses docker, and it isn't running - please start docker and try again!"
  exit 1
fi

cd ../../
start_time=$(date +%s)

#Using parallel maven build to save few seconds
mvn -T 20 clean install -P BUILD-UI
end_time=$(date +%s)

elapsed_time=$((end_time - start_time))

echo "Time taken: $elapsed_time seconds"

cd ./docker/image

rm -rf dist

mkdir dist
#infra items
cp ../../infra/admin-app/target/admin-app-0.0.1-SNAPSHOT.jar ./dist/admin-app.jar
cp ../../infra/config-server/target/config-server-0.0.1-SNAPSHOT.jar ./dist/config-server.jar
cp ../../infra/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar ./dist/eureka-server.jar
cp ../../infra/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar ./dist/gateway-service.jar

#copy-spring-config directory
cp -r ../../../spring-config/ ./dist/spring-config

#services

cp ../../services/quotes-service/target/quotes-service-0.0.1-SNAPSHOT.jar ./dist/quotes-service.jar
cp ../../services/quotes-ui/target/quotes-ui-0.0.1-SNAPSHOT.jar ./dist/quotes-ui.jar

#build docker images

echo "============ BUILDING INFRA IMAGES ============"

for file in "./infra/Dockerfile"*; do
echo "building $file"
if [ -f "$file" ]; then
    filename=$(basename "$file");
    echo "$filename"
    image_name=$(echo "$filename" | sed "s/^Dockerfile-//") 
    docker build -f "./infra/$filename" -t "imjws/qts-$image_name" .
    echo "============ COMPLETED BUILDING $image_name ============"
fi
done 
echo "============ BUILDING SERVICES IMAGES ============"

for file in "./services/Dockerfile"*; do
echo "building $file"
if [ -f "$file" ]; then
    filename=$(basename "$file");
    echo "$filename"
    image_name=$(echo "$filename" | sed "s/^Dockerfile-//") 
    docker build -f "./services/$filename" -t "imjws/qts-$image_name" .
    echo "============ COMPLETED BUILDING $image_name ============"
fi
done 

#clean dist folder
rm -rf dist/
docker buildx prune -f