FROM gradle:8.5-jdk21 AS build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .

# Make gradlew executable here
RUN chmod +x ./gradlew

# Run Gradle build with wrapper
RUN ./gradlew --no-daemon clean build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8081

ENV TZ=UTC LANG=C.UTF-8

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
