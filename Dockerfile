FROM openjdk:17

ARG JAR_FILE=target/GOAL-TRACK.jar

COPY ${JAR_FILE} GOAL-TRACK.jar

#ENTRYPOINT ["java", "-jar", "GOAL-TRACK.jar"]
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "GOAL-TRACK.jar"]

EXPOSE 8080
EXPOSE 5005
