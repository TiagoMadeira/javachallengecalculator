FROM openjdk:23-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ./build/libs/rest-0.0.1-SNAPSHOT.jar rest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/rest.jar"]