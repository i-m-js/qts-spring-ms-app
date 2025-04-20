Disclaimer:

Hello there..! This is a dummy application and it is in WIP without any dealines 😊

---

This is an experimental microservices project using spring-boot and angular to build a full-stack minimal app which serves random quotes, allowing users to like and dislike and build trending quotes among users using flink streaming processing. <br/>
 
 - [X] Boilerplate quotes application (Quotes CRUD)
    - [X] Build DB schema and integerate flyway to manage the schemas
    - [X] Impleemnt service layer to store the quote events (like/dislike) in in-memory db
    - [X] Generate user stat and quote level stats
    - [X] Load additional quotes from external placeholder site
 - [ ] Sending likes/dislike events to Kafka topics
 - [ ] Create a flink job to read events from kafka-topic and generate trending quotes over the predefined window
 - [ ] Store the trending items over the specific window in Redis cache
 - [ ] Implement a trending page in the quotes-ui service

<h4>Note:</h4>

The [config-server](./infra/config-server) is configured to use a local git repo for the configuration. So, clone [qts-spring-config](https://github.com/i-m-js/qts-spring-config) repo. Post that update the `spring.cloud.config.server.git.uri` property in the [application.properties](./infra/config-server/src/main/resources/application.properties)

This repo contains a [docker-compose.yml](./docker/compose/docker-compose.yml) file, using which you can launch the containers directly, but run [build-images.sh](./docker/image/build-images.sh) script to generate the images. buid-images.sh file assumes the spring-config repo is a sibling to this project structure, if not update the sh file before running it.

<h3> Launching the application manually </h3>
Start the services in the following order:

- infra
    - eureka-server
    - config-server
    - admin-app
    - gateway-service
- services
    - quotes-service
    - quotes-ui

<h3> Lauching the applciation via docker compose </h3>

Switch to `./docker` folder 

run `build-images.sh` script in the `image` folder and then run `docker compose up -d` by switching to the `compose` folder to start all the services

<h3>Accessing the application </h3>

Open [http://localhost:8080](http://localhost:8080) to see the quotes app (use user-1 and empty password to lauch mock-login). Explore the compose file to understand the exposed port mapping, and access the applications
