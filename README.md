# Newsletter subscription

## How to run with Docker
Just run the docker-compose located at the root of the repository with the following command:

`docker-compose up -d`

## How to run locally
Run mongo and elasticmq locally (You can also use the docker-compose) and then run each microservice using the **local** profile 
for the subscription and email services, otherwise they will not be able to connect to mongo/elasticmq. There is no
local profile for public service.

## Architecture
 - Java 11.
 - Spring Boot (Webflux).
 - MongoDB: used as persistence.
 - Elasticmq (SQS interface emulator): used to connect subscription service with email service more efficiently.

## Swagger
Subscription service: http://localhost:8080/swagger-ui/

Public service: http://localhost:8082/swagger-ui/

## TO DO
 - Implement security for private microservices (subscription specially) as right now it is possible to connect directly to
subscription microservice without using public service or without using a token.
 - Improve exception handling
 - Monitor with Grafana and Prometheus