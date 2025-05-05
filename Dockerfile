FROM openjdk:17

ARG JAR_FILE=target/backend-service.jar

COPY ${JAR_FILE} backend-service.jar

ENTRYPOINT ["java", "-jar", "goal-track.jar"]

EXPOSE 8080