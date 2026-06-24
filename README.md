# BovInA Backend

![BovInA](https://i.imgur.com/1UzEy1n.png)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge\&logo=spring\&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge\&logo=mysql\&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge\&logo=amazon-aws\&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge\&logo=swagger\&logoColor=white)

## Project overview

**BovInA** is a digital platform developed as part of my course completion project (TCC), in collaboration with a colleague.

The project was presented at **MOSTRATEC**, the largest science and technology fair in Latin America, where it won **3rd place in the Computer Science category**.

This repository contains the **backend** of the system, which was the part I was responsible for developing.

The platform focuses on digitizing and optimizing the management of **In Vitro Embryo Production (PIVE)** in cattle. By replacing manual processes with a centralized digital system, BovInA improves the organization, monitoring, and analysis of important stages of the PIVE process, such as oocyte collection, in vitro fertilization, embryo production, embryo transfer, and pregnancy tracking.

The goal is to provide a more effective tool for managing bovine embryo production, contributing to genetic improvement and reproductive efficiency in the livestock sector.

## Main features

* **Animal management**

  * Registration and tracking of donors, receivers, and bulls.

* **Oocyte collection**

  * Recording collections and monitoring total and viable oocytes.

* **Embryo production**

  * Tracking viable embryo production and production efficiency.

* **Embryo transfer**

  * Managing embryo transfer data and related production outcomes.

* **Pregnancy tracking**

  * Monitoring pregnancy results from embryo transfers.

* **Reports and advanced filters**

  * Efficiency in viable embryo production by animal.
  * Average number of viable oocytes collected per donor.
  * Best bull and donor combinations.
  * Production and pregnancy success indicators.

## Tech stack

* **Java 21**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA / Hibernate**
* **MySQL**
* **Swagger / OpenAPI**
* **Docker / Docker Compose**
* **AWS EC2 / RDS**

## System architecture

![System Architecture](https://i.imgur.com/FYP72wJ.png)

The system follows a layered backend architecture:

1. **Frontend**

   * Built with HTML, CSS, JavaScript, React, and React Native.
   * Provides interfaces for data input, visualization, and analysis.
   * Communicates with the backend through REST APIs.

2. **Backend**

   * Built with Java and Spring Boot.
   * Exposes REST endpoints for the frontend.
   * Handles validation, business rules, persistence, and reporting.

3. **Database**

   * MySQL database used to persist animals, collections, productions, transfers, pregnancies, schedules, and related data.

### Backend layers

* **Controllers**

  * Receive HTTP requests and expose the API endpoints.

* **Services**

  * Contain business logic and coordinate application flows.

* **Repositories**

  * Handle database access through Spring Data JPA.

* **Entities**

  * Represent the persistence model mapped to the database.

* **DTOs**

  * Represent request and response objects used by the API.

## Running the project

There are two main ways to run the backend locally:

1. **Full Docker workflow**

   * Runs both MySQL and the Spring Boot API with Docker Compose.

2. **Local Java workflow**

   * Runs only MySQL with Docker Compose and starts the API locally with Maven or an IDE.

## Option 1 — Run the full stack with Docker

### Prerequisites

* Docker 24+
* Docker Compose v2 plugin

Use the modern Compose command:

```bash
docker compose
```

not the legacy:

```bash
docker-compose
```

### Start the full stack

From the repository root:

```bash
docker compose up --build
```

This starts:

* MySQL on port `3306`
* BovInA API on port `8080`

After the application starts, verify it with:

```bash
curl http://localhost:8080/actuator/health
```

Expected response:

```json
{
  "status": "UP"
}
```

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui.html
```

### Stop the containers

```bash
docker compose down
```

This stops and removes the containers, but keeps the MySQL data volume.

### Reset the local database

```bash
docker compose down -v
```

The `-v` flag removes the MySQL volume. The next `docker compose up` starts with a fresh database.

## Option 2 — Run MySQL with Docker and the API locally

This workflow is useful when developing through IntelliJ IDEA or running the application with Maven.

### Start only MySQL

```bash
docker compose up mysql
```

MySQL will be available on:

```text
localhost:3306
```

### Build the application

```bash
./mvnw clean package
```

This produces:

```text
target/pive-0.0.1-SNAPSHOT.jar
```

### Run the application locally

```bash
./mvnw spring-boot:run
```

By default, the application connects to:

```text
jdbc:mysql://localhost:3306/pivedatabase
```

using:

```text
username: root
password: 123
```

These defaults match the local MySQL configuration provided by `compose.yaml`.

## Docker notes

The Docker setup in this repository is intended for **local development and manual API testing**.

It is not a production deployment setup.

The Compose file uses development-friendly defaults, such as a local MySQL container and plaintext credentials. Production deployments should use proper secrets management, stricter database users, TLS, monitoring, backups, and infrastructure-specific configuration.

### Exposed ports

| Service | Container port | Host port |
| ------- | -------------: | --------: |
| `api`   |           8080 |      8080 |
| `mysql` |           3306 |      3306 |

### Important container networking detail

Inside Docker Compose, the API container connects to MySQL using the Compose service name:

```text
jdbc:mysql://mysql:3306/pivedatabase
```

It should not use:

```text
jdbc:mysql://localhost:3306/pivedatabase
```

because `localhost` inside a container refers to the container itself, not to the MySQL container.

## Environment variables

All per-environment configuration can be overridden through environment variables.

Defaults are development-friendly. Production deployments should define values explicitly.

### Database

| Variable      | Default                                    | Description          |
| ------------- | ------------------------------------------ | -------------------- |
| `DB_URL`      | `jdbc:mysql://localhost:3306/pivedatabase` | JDBC connection URL. |
| `DB_USERNAME` | `root`                                     | Database username.   |
| `DB_PASSWORD` | `123`                                      | Database password.   |

When running with Docker Compose, the API container overrides `DB_URL` to use the MySQL service name:

```text
jdbc:mysql://mysql:3306/pivedatabase
```

### JPA / Hibernate

| Variable       | Default  | Description                                                                                                  |
| -------------- | -------- | ------------------------------------------------------------------------------------------------------------ |
| `JPA_DDL_AUTO` | `update` | Hibernate schema-management mode. Production should use `validate` or `none` after a proper schema baseline. |
| `JPA_SHOW_SQL` | `true`   | Enables SQL logging. Recommended to set `false` in production.                                               |

### API / CORS

| Variable              | Default                 | Description                                       |
| --------------------- | ----------------------- | ------------------------------------------------- |
| `BOVINA_CORS_ORIGINS` | `http://localhost:5173` | Comma-separated list of allowed frontend origins. |

### Error responses

| Property                     | Default | Description                                                                                                                                |
| ---------------------------- | ------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| `bovina.error.legacy-format` | `true`  | When `true`, error responses use the legacy raw-string format. When `false`, error responses use structured RFC 7807 `ProblemDetail` JSON. |

## Spring profiles

### Default profile

The default profile keeps compatibility with the original application behavior:

* Legacy raw-string error responses.
* Development-friendly database defaults.
* SQL logging enabled by default.

### Dev profile

The `dev` profile enables newer development behavior, such as structured error responses using `ProblemDetail`.

Activate it with:

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```

or:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

The Docker Compose API service uses the `dev` profile by default.

## Troubleshooting

### Port already allocated

If Docker fails with a message like:

```text
port is already allocated
```

another process is already using port `8080` or `3306`.

You can either stop the competing process or change the host port mapping in `compose.yaml`.

For example:

```yaml
ports:
  - "8081:8080"
```

### First Docker build is slow

The first Docker build may take a few minutes because Docker needs to download base images and Maven dependencies.

Subsequent builds are faster because Docker reuses cached layers.

### API cannot connect to MySQL in Docker

Make sure the API container uses:

```text
jdbc:mysql://mysql:3306/pivedatabase
```

Inside Docker, `mysql` is the Compose service name.

## Refactoring note

This codebase is currently being refactored to improve code quality, maintainability, consistency, and alignment with modern Java and Spring Boot practices.

The refactor focuses on improvements such as:

* Cleaner service boundaries.
* Better transaction management.
* More consistent error handling.
* Safer validation.
* Improved DTO usage.
* Reduced coupling between API contracts and persistence entities.
* Better configuration practices.
* Improved Docker-based local development.

The goal of the refactor is to improve the internal quality of the backend while preserving the same core business features of the original BovInA system.

## Project status

BovInA is an academic project originally developed for a TCC and science fair presentation. It remains a valuable backend project for studying Java, Spring Boot, REST APIs, JPA/Hibernate, MySQL, API documentation, Docker, and real-world refactoring practices.
