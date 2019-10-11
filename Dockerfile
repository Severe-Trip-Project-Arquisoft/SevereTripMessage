FROM openjdk:8-jdk-alpine

ARG JAR=web-0.0.1-SNAPSHOT.jar
COPY target/$JAR /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
