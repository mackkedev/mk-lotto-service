# Use official OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built .jar file from the Maven target directory
COPY target/lotto.jar /app/lotto-app.jar

# Run the .jar file when the container starts
ENTRYPOINT ["java", "-jar", "/app/lotto-app.jar"]

# Expose the port your application is listening on (if applicable)
EXPOSE 8080
