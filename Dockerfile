FROM openjdk:23-jdk
ARG JAR_FILE=build/libs/*.jar

COPY build/libs/calculator-0.0.1-SNAPSHOT.jar calculator.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/calculator.jar"]