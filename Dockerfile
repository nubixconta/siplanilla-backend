# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar
COPY --from=build /app/src/main/resources/wallet /app/wallet

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
