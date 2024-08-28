# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
COPY out/artifacts/orderingsystem_jar/orderingsystem.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]