# Spring Data REST with JWT
Spring Boot project that shows how to build and secure a RESTful API using Spring Data REST and Spring Security. The example implements Role Based Access Control (RBAC) by mapping certain privileges to roles which are then assigned to the application users.

## Software Stack
- Gradle
- Flyway
- Docker
- PostgreSQL
- Spring Boot
- Spring Data REST
- Spring Security
- Json Web Tokens 

## PostgreSQL
You can either use a local PostgreSQL instance running on your computer, or use the provided docker-compose.yaml file to create new instances of PostgreSQL and pgAdmin using Docker. 

When your PostgreSQL instance is up and running do not forget to open **application.properties** and **build.gradle** in order to provide the appropriate database url and valid credentials.

## Flyway
Flyway is used for database migrations. 

Use above command to create appropriate tables and insert data.
```shell
./gradlew flywayMigrate
```

The inserted data include 3 users:
- username: admin, pass: admin
- username: manager, pass: manager
- username: user, pass: user

## Run project using Gradle Wrapper
```shell
./gradlew bootRun
```

## Example cURL requests

### Login
```shell
curl --request POST \
     --header "Content-Type: application/json" \
     --data '{"username":"admin","password":"admin"}' \
     --include \
     http://localhost:8080/login
```

You will receive access_token and refresh_token in response headers.

### Sample API request using access_token
```shell
curl --header "Authorization: Bearer <access_token>" \
     http://localhost:8080/api/users
```

### Refresh expired token
```shell
curl --header "Authorization: Bearer <refresh_token>" \
     --include \
     http://localhost:8080/token/refresh
```