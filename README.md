# Projeto - Gestão de Transporte Corporativo

##  Como executar localmente com Docker

-Certifique-se de ter instalado o Docker Desktop e o Docker Compose;
-Com a presença do compose.yaml e do Docker file, rode o comando: "docker compose up --build";
-Isso irá baixar a imagem do MySql e da API;

##  Pipeline CI/CD

GitHub Actions -->Automação do pipeline --> Responsável por executar automaticamente o build, testes e deploy a cada push ou pull request!
Maven	--> Build e testes automatizados	--> Compila o código Java, executa os testes unitários e gera o artefato .jar pronto para deploy!
Docker --> Empacotamento e execução	--> Cria uma imagem containerizada da aplicação para garantir que ela rode de forma idêntica em qualquer ambiente!
Flyway --> Migração de banco de dados --> Garante que o schema do banco esteja sempre sincronizado com a versão do código!
Docker Hub (ou GitHub Container Registry) --> Repositório de imagens	Armazena a imagem Docker gerada pelo pipeline, que será usada em ambientes de produção!

##  Containerização

FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package

//O que acontece aqui:
//Usa uma imagem Maven + JDK para compilar o projeto (maven:3.9.8-eclipse-temurin-21).
//Cria o diretório /opt/app dentro do container e copia todo o código-fonte.
//Executa o comando mvn clean package para gerar o JAR compilado com todas as dependências.
//Tudo que é gerado aqui será copiado para a segunda imagem, descartando o Maven e os arquivos de build, deixando a imagem final mais leve.

FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

COPY --from=build  /opt/app/target/demo-0.0.1-SNAPSHOT.jar /opt/app/demo-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

ENV PROFILE=prd

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

//O que acontece aqui:
//Usa apenas a JRE (Java Runtime), sem Maven, para reduzir o tamanho da imagem.
//Copia o JAR compilado da primeira etapa (--from=build).
//Define /opt/app como diretório de trabalho.
//Configura a variável de ambiente PROFILE=prd para permitir seleção de profile Spring Boot (dev, test, prd).
//Expõe a porta 8080 para acesso externo.
//Define o ENTRYPOINT para iniciar a aplicação Spring Boot com o profile selecionado.

//Estratégias adotadas:
//Multi-stage build → reduz a imagem final, pois descarta Maven, código-fonte e dependências desnecessárias no runtime.
//Variável de ambiente para profile → flexível para rodar a mesma imagem em dev, test ou prd.
//Imagem base leve (alpine) → minimiza tamanho do container, agilizando build e deploy.
//Separação de build e runtime → mais seguro e portátil, seguindo boas práticas Docker.

##  Prints do funcionamento

![](/assets/images/print1.png)
![](/assets/images/print2.png)
![](/assets/images/print3.png)
![](/assets/images/print4.png)
![](/assets/images/print5.png)

##  Tecnologias utilizadas

🔹 Linguagens

Java 21 – Linguagem principal da aplicação Spring Boot.

SQL – Para modelagem e consultas ao banco de dados MySQL.

🔹 Frameworks e Bibliotecas

Spring Boot – Framework principal para desenvolvimento de aplicações Java baseadas em microserviços.

Spring Data JPA – Para acesso e manipulação de dados em banco relacional.

Hibernate – Implementação de JPA para mapeamento objeto-relacional.

Flyway – Gerenciamento de migrações e versionamento do banco de dados.

Lombok – Redução de boilerplate com geração automática de getters, setters e construtores.

Spring Boot Test / JUnit 5 / Mockito – Frameworks para testes unitários e de integração.

🔹 Banco de Dados

MySQL 8 – Banco de dados relacional utilizado para persistência de dados.

🔹 DevOps / Containerização

Docker – Criação de containers para a aplicação e banco de dados.

Docker Compose – Orquestração de múltiplos containers (aplicação + banco).

GitHub Actions – Pipeline CI/CD para build, testes e deploy automatizado.

Maven – Gerenciamento de dependências e build do projeto.

🔹 IDE e Ferramentas de Desenvolvimento

Visual Studio Code – IDE utilizada para desenvolvimento e debugging.

Postman / Insomnia – Testes de APIs REST.