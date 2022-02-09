FROM openjdk:11-jre-slim
COPY /target/hw17_docker-1.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
