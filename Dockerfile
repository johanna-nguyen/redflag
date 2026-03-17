FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml pom.xml

RUN mvn -DskipTests dependency:go-offline

COPY src/ src/

RUN mvn -DskipTests package

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /workspace/target/*.jar /app/app.jar

EXPOSE 8080

COPY --chmod=755 docker-entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
