# Stage 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Create a minimal JRE image
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the first stage
COPY --from=build /app/target/quizapp-0.0.1-SNAPSHOT.jar /app/quizapp.jar

# Set environment variables
ENV RENDER_DATABASE_URL=jdbc:postgresql://dpg-cmarlci1hbls73cnvev0-a/quiz_jplu
ENV RENDER_DATABASE_USERNAME=xinqi
ENV RENDER_DATABASE_PASSWORD=wMRirYCTYdwTjsSPWT2CGTh9I27gY7yE

EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "/app/quizapp.jar"]

