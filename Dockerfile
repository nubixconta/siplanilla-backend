# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

RUN echo "Contenido de target:"
RUN ls -la /app/target

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

RUN echo "Contenido de /app:"
RUN ls -la /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]