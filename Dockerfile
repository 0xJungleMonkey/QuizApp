FROM eclipse-temurin:17-jdk
RUN apt-get update \
  && apt-get install -y ca-certificates curl git --no-install-recommends \
  && rm -rf /var/lib/apt/lists/*

# common for all images
ENV MAVEN_HOME /usr/share/maven

COPY --from=maven:3.9.6-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

ARG MAVEN_VERSION=3.9.6
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Set environment variables
ENV database_url=jdbc:postgresql://dpg-cmarlci1hbls73cnvev0-a.oregon-postgres.render.com/quiz_jplu

ENV database_username=xinqi
ENV database_password=wMRirYCTYdwTjsSPWT2CGTh9I27gY7yE

# Copy the built JAR file from the previous stage to the container
COPY target/quizapp-0.0.1-SNAPSHOT.jar /app/quizapp.jar
EXPOSE 8080
# Set the command to run the application
CMD ["java", "-jar", "/app/quizapp.jar"]




