FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:resolve
COPY src/ ./src/
RUN mvn -B package

FROM openjdk:17
WORKDIR /app
RUN groupadd -r springapp && useradd --no-log-init -r -g springapp springapp
COPY --chown=springapp:springapp target/*.jar /app/application.jar
COPY --chown=springapp:springapp ./src/main/resources/application.properties /app/application.properties
EXPOSE 8082
USER springapp
ENTRYPOINT [ "java", "-Dspring.config.import=file:/app/application.properties", "-jar", "application.jar"]