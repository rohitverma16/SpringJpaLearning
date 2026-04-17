## Step 1: Base image with Java [Lightweight Java 21 runtime (recommended in 2026)]
FROM eclipse-temurin:21-jre

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy jar file
COPY target/spring-jpa-demo.jar my-app.jar

# Spring Boot default port (Just documentation, not a networking command)
EXPOSE 8080

# Step 4: Run the application
ENTRYPOINT ["java","-jar","my-app.jar"]