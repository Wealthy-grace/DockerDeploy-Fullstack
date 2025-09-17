##FROM ubuntu:latest
##LABEL authors="user"
#
##//ENTRYPOINT ["top", "-b"]
#
#
#
#FROM openjdk:21-jdk-slim
#WORKDIR /app
#COPY target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]



# Use Gradle image for building the application
FROM gradle:8.4.0-jdk21 AS build

# Set the working directory for the build process
WORKDIR /app

# Copy the Gradle project files
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src ./src

# Build the application
RUN gradle clean build --no-daemon

# Use a minimal Java runtime for the final image
FROM openjdk:21-jdk-slim

# Add a volume to hold the application data
VOLUME /tmp

# The application's JAR file location
ARG JAR_FILE=build/libs/blog-0.0.1-SNAPSHOT.jar

# Copy the jar file from the build stage to the final image
COPY --from=build /app/${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
