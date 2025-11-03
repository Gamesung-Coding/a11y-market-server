# 1. Build
# Use gradle jdk
FROM eclipse-temurin:21-jdk AS builder

RUN apt-get update && apt-get install -y findutils && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /workspace

# Copy gradle wrapper files
COPY gradlew .
COPY gradle ./gradle

# Copy gradle setting files
COPY build.gradle .
COPY settings.gradle .

# Download dependencies
RUN ./gradlew dependencies

# Copy source code
COPY src ./src

# Build application
RUN ./gradlew clean bootJar --no-daemon

# 2. final stage
# Using slim JRE
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy .jar file from build stage to final stage
COPY --from=builder /workspace/build/libs/*.jar app.jar

# Expose default port
EXPOSE 8080

# Run Application
# Environment Variable must be in docker-compose file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]