FROM openjdk:17-alpine3.14
RUN mkdir -p app/logs
RUN mkdir -p app/geolite2
RUN mkdir -p app/data
RUN apk update
RUN apk upgrade --available && sync
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} websitebce-1.0-SNAPSHOT.jar
COPY target/classes/GeoLite2-City.mmdb /app/geolite2/
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","websitebce-1.0-SNAPSHOT.jar"]
