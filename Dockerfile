FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} websitebce-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","websitebce-1.0-SNAPSHOT.jar"]
