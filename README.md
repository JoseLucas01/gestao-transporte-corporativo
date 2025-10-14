# Gestão Sustentável de Transporte Corporativo

Este projeto é uma API REST para gerenciar transportes sustentáveis, impactos ambientais e recompensas relacionados a uma empresa. A aplicação foi desenvolvida utilizando **Spring Boot**, conectando-se a um banco de dados **Oracle**. Abaixo está o guia completo para executar o projeto.

---

## Requisitos para executar o projeto
Antes de começar, certifique-se de ter os seguintes itens instalados no seu ambiente:
1. **JDK 17** ou superior ([Download](https://www.oracle.com/java/technologies/javase-downloads.html)).
2. **Maven** ([Download](https://maven.apache.org/download.cgi)).
3. **Docker** e **Docker Compose** ([Guia de Instalação](https://docs.docker.com/get-docker/)).
4. Um cliente SQL para verificar a conexão com o banco de dados Oracle
---

[//]: # (## Configuração do Banco de Dados Oracle)

[//]: # (O banco de dados Oracle está hospedado remotamente com os seguintes detalhes:)

[//]: # (- **Host:** `oracle.fiap.com.br`)

[//]: # (- **Porta:** `1521`)

[//]: # (- **SID:** `ORCL`)

[//]: # (- **Usuário:** `rm556002`)

[//]: # (- **Senha:** `240605`)

Se necessário, ajuste esses valores no arquivo `application.yml`.

---

## Configuração do Projeto

### 1. Clonar o Repositório
Clone o projeto para sua máquina local:
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Configurar o Arquivo `application.yml`
Certifique-se de que o arquivo `src/main/resources/application.yml` está configurado como abaixo:

[//]: # (```yaml)

[//]: # (spring:)

[//]: # (  datasource:)

[//]: # (    url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL)

[//]: # (    username: rm556002)

[//]: # (    password: 240605)

[//]: # (    driver-class-name: oracle.jdbc.OracleDriver)

[//]: # (  jpa:)

[//]: # (    hibernate:)

[//]: # (      ddl-auto: validate)

[//]: # (    properties:)

[//]: # (      hibernate:)

[//]: # (        dialect: org.hibernate.dialect.Oracle12cDialect)

[//]: # (  security:)

[//]: # (    user:)

[//]: # (      name: admin)

[//]: # (      password: password)

[//]: # (server:)

[//]: # (  port: 8080)
```

### 3. Compilar o Projeto
Use o Maven para compilar o projeto e gerar o arquivo JAR:
```bash
./mvnw clean package
```

O arquivo JAR será gerado no diretório `target/`, com o nome `gestao-transporte-1.0.0.jar`.

---

## Executando o Projeto

### 1. Usando Docker Compose
Se você deseja rodar o projeto usando Docker, o arquivo `docker-compose.yml` já está configurado. Para iniciar o contêiner:
```bash
docker-compose up --build
```

### 2. Executando Localmente
Se preferir rodar o projeto localmente sem Docker, execute o comando abaixo:
```bash
java -jar target/gestao-transporte-1.0.0.jar
```

---

## Testando os Endpoints
Você pode testar os endpoints usando **Postman**, **Insomnia** ou qualquer outra ferramenta de sua escolha. Aqui estão os principais endpoints disponíveis:

### Endpoints de Transportes Sustentáveis
1. **Listar Transportes Sustentáveis**
   - **GET** `/transportes-sustentaveis`

2. **Cadastrar Transporte Sustentável**
   - **POST** `/transportes-sustentaveis`
   - **Body:**
     ```json
     {
       "nome": "Bicicleta",
       "descricao": "Transporte limpo e sustentável"
     }
     ```

3. **Buscar Transporte por ID**
   - **GET** `/transportes-sustentaveis/{id}`

4. **Atualizar Transporte**
   - **PUT** `/transportes-sustentaveis/{id}`
   - **Body:**
     ```json
     {
       "nome": "Carro Elétrico",
       "descricao": "Atualizado para carro elétrico"
     }
     ```

5. **Deletar Transporte**
   - **DELETE** `/transportes-sustentaveis/{id}`

---

### Endpoints de Impactos Ambientais
1. **Cadastrar Impacto Ambiental**
   - **POST** `/impacto-transporte`
   - **Body:**
     ```json
     {
       "empresaId": 1,
       "impactoDescricao": "Redução de emissão em 10%",
       "impactoValor": 15.5
     }
     ```

2. **Listar Impactos Ambientais**
   - **GET** `/impacto-transporte`

3. **Buscar Impactos por Empresa**
   - **GET** `/impacto-transporte/{empresaId}`

---

### Endpoints de Recompensas
1. **Cadastrar Recompensa**
   - **POST** `/recompensas`
   - **Body:**
     ```json
     {
       "descricao": "Recompensa por transporte sustentável",
       "valor": 50.0
     }
     ```

2. **Listar Recompensas**
   - **GET** `/recompensas`

3. **Atualizar Recompensa**
   - **PUT** `/recompensas/{id}`
   - **Body:**
     ```json
     {
       "descricao": "Atualização de recompensa",
       "valor": 100.0
     }
     ```

4. **Deletar Recompensa**
   - **DELETE** `/recompensas/{id}`

---

## Documentação da API
Se você configurou o Swagger na aplicação, pode acessar a documentação interativa em:
```
http://localhost:8080/swagger-ui.html
```

---

## Problemas Comuns e Soluções
1. **Erro ao conectar ao banco de dados:**
   - Verifique a conectividade com o banco.
   - Confirme se os parâmetros no `application.yml` estão corretos.

2. **Porta 8080 já em uso:**
   - Altere a porta no `application.yml`:
     ```yaml
     server:
       port: 8081
     ```

3. **Dependências não resolvidas:**
   - Tente atualizar as dependências do Maven:
     ```bash
     ./mvnw clean install
     ```

---

## Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para contribuir:
1. Faça um fork do repositório.
2. Crie uma branch para sua feature/correção:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça o commit de suas alterações:
   ```bash
   git commit -m "Minha nova feature"
   ```
4. Envie as alterações:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request no repositório original.

---

## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

---