FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

COPY --chmod=755 docker-entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
