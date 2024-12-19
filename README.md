Gradle version: 8.11.1 Java version: 23.0.1

How to Run: gradle build docker-compose build docker-compose up

Observation: One of the Docker containers was removed. The application runs entirely on one container, using a Kafka broker for communication running in a separate container.
I believe all the requested features are implemented. I tried to improve the code as much as I could. Thank you for the opportunity.

The app is accessible via http://localhost:8080 the logs are written in calculator.log at the root of the project.
