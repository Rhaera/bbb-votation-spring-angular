FROM eclipse-temurin:17-jdk-jammy
LABEL maintainer="rauldantas@usp.br"
VOLUME /main-app
ADD target/bbb-votation-api-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
