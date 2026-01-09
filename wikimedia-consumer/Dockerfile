# Base java image
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Set wokring directory
WORKDIR /app

# Copy Pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy src folder
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built jar file
COPY --from=build /app/target/wikimedia-consumer-*.jar app.jar

# Expose 8081
EXPOSE 8081

# Run
ENTRYPOINT ["java", "-jar", "app.jar"]