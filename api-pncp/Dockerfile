# Etapa de build
FROM ubuntu:latest AS build

# Define o diretório de trabalho
WORKDIR /app

# Atualiza o repositório e instala o JDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Copia o código-fonte para o contêiner
COPY . /app/api-pncp

# Navega até o diretório do projeto e executa o Maven para construir o projeto sem executar os testes
WORKDIR /app/api-pncp
RUN mvn clean install -DskipTests

# Etapa de runtime
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Exponha a porta da aplicação
EXPOSE 8080

# Copia o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/api-pncp/target/api-pncp-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
