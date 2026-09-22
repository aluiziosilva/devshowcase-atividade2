# Estágio 1: Build da aplicação usando o Maven nativo do container
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia todo o projeto
COPY . .

# Executa o build usando 'mvn' diretamente (não usa ./mvnw)
RUN mvn clean package -DskipTests

# Estágio 2: Execução da aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]