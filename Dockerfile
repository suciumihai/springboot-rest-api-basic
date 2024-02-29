FROM eclipse-temurin:21

LABEL maintainer="serby.boss@gmail.com"

WORKDIR /app

COPY target/springboot-rest-api-0.0.1-SNAPSHOT.jar /app/springboot-rest-api.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "springboot-rest-api.jar"]