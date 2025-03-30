# Use official OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built .jar file from the Maven target directory
COPY target/lotto-app-0.0.1-SNAPSHOT.jar /app.jar

# Run the .jar file when the container starts
ENTRYPOINT ["java", "-jar", "/lotto-app-0.0.1-SNAPSHOT.jar"]

# Expose the port your application is listening on (if applicable)
EXPOSE 8080
