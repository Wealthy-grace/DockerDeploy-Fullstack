# Build stage - use Gradle image with JDK 21 to build the app
FROM gradle:8.5-jdk21 AS build

# Set working directory inside the container
WORKDIR /home/gradle/src

# Copy all project files and set ownership to gradle user
COPY --chown=gradle:gradle . .

# Make Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the project, skipping tests for faster build
RUN ./gradlew --no-daemon clean build -x test

# Runtime stage - use lightweight OpenJDK 21 slim image
FROM openjdk:21-jdk-slim

# Install curl for health checks
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

# Set working directory inside the runtime container
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

# Expose port the app will run on
EXPOSE 8081

# Set environment variables (timezone and locale)
ENV TZ=UTC LANG=C.UTF-8

# Add health check for Docker Compose
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8081/actuator/health || exit 1

# Run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]