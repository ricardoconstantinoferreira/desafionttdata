# ☕ Java   🌱 Spring Boot   🐬 MySQL   🐳 Docker   🧪 Mockoon

## Descrição
API simples em Spring Boot que consulta endereços por CEP (zipcode) através de uma API externa (mock) e persiste logs de consulta.

## Tecnologias
- ☕ Java 17
- 🌱 Spring Boot
- 🐬 MySQL
- 🐳 Docker / Docker Compose
- 🧪 Mockoon (mock da API externa de CEP)

## Estrutura principal
- `src/main/java` - código fonte
  - `entity` - entidades JPA (`Adress`, `AddressLog`, ...)
  - `controller` - `AddressController` (endpoint `/addresses/{zipcode}`)
  - `service` - `AddressService` (interface) e `service.impl.AddressServiceImpl` (implementação que chama API externa)
  - `dto` - `AddressResponseDto`
- `src/main/resources/application.properties` - configurações (suportam variáveis de ambiente)
- `Dockerfile` - imagem multi-stage para a aplicação
- `docker-compose.yml` - sobe MySQL e a aplicação (dev)
- `mysql/init.sql` - script de inicialização do banco (seed)

## Pré-requisitos
- Java 17
- Maven (ou use o wrapper `./mvnw` que já está no projeto)
- Docker e Docker Compose
- Mockoon (ou outro mock HTTP) para a API externa no `http://localhost:3001`

## Configuração (variáveis)
A aplicação lê as configurações do banco via variáveis de ambiente (com defaults em `application.properties`):
- `MYSQL_HOST` (default: `localhost`)
- `MYSQL_PORT` (default: `3306`)
- `MYSQL_DATABASE` (default: `appdb`)
- `MYSQL_USER` (default: `appuser`)
- `MYSQL_PASSWORD` (default: `apppass`)
- `SERVER_PORT` (default: `8080`)

> Quando executada via `docker-compose`, o serviço `app` recebe `MYSQL_HOST=db` para conectar ao serviço MySQL do compose.

## Como executar
Opções rápidas para desenvolvimento:

1) Build local e rodar via Spring Boot (usa MySQL local ou dockerized conforme `MYSQL_HOST`):

```bash
# Compilar (usar wrapper)
./mvnw -DskipTests package

# Rodar via Maven
./mvnw spring-boot:run

# Ou executar o jar gerado
java -jar target/*.jar
```

2) Usando Docker Compose (levanta MySQL + app)

```bash
# Constroi a imagem da aplicação e sobe os serviços
docker-compose up --build

# Para rodar em background
docker-compose up -d --build

# Ver logs do MySQL
docker logs mysql-db --tail 200

# Parar e remover
docker-compose down
```

## Mock da API externa (Mockoon)
A implementação do `AddressServiceImpl` faz uma chamada HTTP para `http://localhost:3001/{zipcode}`. Para desenvolvimento, use o Mockoon para simular essa API:
- Crie um endpoint GET `/:zipcode` que responda JSON com o shape compatível com `AddressResponseDto`:

Exemplo de resposta (JSON):

```json
{
  "zipcode": "01001000",
  "address": "Praça da Sé",
  "neighborhood": "Sé",
  "city": "São Paulo",
  "state": "SP"
}
```
- Configure o Mockoon para rodar na porta `3001`.

## Endpoints disponíveis
- GET /addresses/{zipcode}
  - Consulta o serviço externo pelo CEP informado e retorna os dados no formato da entidade `Adress`.
  - Retorna `200` com o objeto do endereço ou `404` caso não encontrado.

Exemplo com curl:

```bash
curl http://localhost:8080/addresses/01001000
```

## Conexão com o DB (DBeaver)
Use as mesmas credenciais do `docker-compose.yml` para conectar no MySQL:
- Host: `localhost`
- Port: `3306`
- Database: `appdb`
- Username: `appuser`
- Password: `apppass`