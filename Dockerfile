# Estágio 1: Compilação (Build) da aplicação Java com Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia todos os arquivos do projeto para o container
COPY . .

# Executa o build gerando o arquivo .jar (ignorando os testes para ser mais rápido)
RUN ./mvnw clean package -DskipTests

# Estágio 2: Execução da aplicação
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080 padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]