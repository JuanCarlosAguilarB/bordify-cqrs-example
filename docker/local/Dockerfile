FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar bordify-monolith-0.0.1-SNAPSHOT.jar.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bordify-monolith-0.0.1-SNAPSHOT.jar.jar"]
