# Etapa de construção
FROM openjdk:21 AS build

WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Executa o build do projeto
RUN mvn clean package

# Etapa de execução
FROM openjdk:21

WORKDIR /app

# Copia o JAR do container de build
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
