FROM openjdk:15-jdk-alpine
COPY build/libs/email-service-0.0.1-SNAPSHOT.jar email-service.jar
ENTRYPOINT ["java","-jar","./email-service.jar"]