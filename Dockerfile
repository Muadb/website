FROM openjdk:16-alpine
RUN mkdir -p app/logs
RUN mkdir -p app/geolite2
RUN mkdir -p app/data
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} websitebce-1.0-SNAPSHOT.jar
COPY target/classes/GeoLite2-City.mmdb /app/geolite2/
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","websitebce-1.0-SNAPSHOT.jar"]
