#----------------------------------------Beginner-----------------------------------------------

# Step 1: Base image with Java [Lightweight Java 21 runtime (recommended in 2026)]
FROM eclipse-temurin:21-jre

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy jar file
COPY target/spring-jpa-demo.jar my-app.jar

# Spring Boot default port (Just documentation, not a networking command)
EXPOSE 8080

# Step 4: Run the application
ENTRYPOINT ["java","-jar","my-app.jar"]


#--------------------------------Intermediate----------------------------

#Step 1: Build Stage

#Has full Java JDK(needed for building) named as builder
FROM eclipse-temurin:21-jdk AS builder

#Inside container /build-> working directory(everything happens here)
WORKDIR /build

#Copies pom.xml, mvnw, .mvn (in build)
COPY pom.xml mvnw ./
COPY .mvn .mvn

#Download all dependencies before copying source code (Docker caches this layer, next builds will be much faster)
RUN ./mvnw dependency:go-offline -B

#Now source code is added(in build)
COPY src ./src

#Builds the app( in /build/target/app.jar)
RUN ./mvnw clean package -DskipTests


#Step 2: Runtime Stage

#New container(completely separate-Only java runtie no build tools), much smaller
FROM eclipse-temurin:21-jre
WORKDIR /app

# copies only the final .jar from builder, ignores source code, maven, etc.
COPY --from=builder /build/target/*.jar app.jar

#App will run on port 8080(just documentation)
EXPOSE 8080

#When container starts: {  java -jar app.jar}
ENTRYPOINT ["java","-jar","app.jar"]
