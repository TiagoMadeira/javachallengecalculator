FROM openjdk:23-jdk
ARG JAR_FILE=build/libs/*.jar

COPY ./build/libs/calculator-service-0.0.1-SNAPSHOT.jar calculator-service.jar

ENTRYPOINT ["java", "-jar", "/calculator-service.jar"]