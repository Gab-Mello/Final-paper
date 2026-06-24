![BovInA](https://i.imgur.com/1UzEy1n.png) 
## Project description
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white) ![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

BovInA is a digital platform developed as part of my course completion project (TCC) in collaboration with a colleague, and it was presented at MOSTRATEC, the largest science and technology fair in Latin America, where it won 3rd place in the Computer Science category. This repository contains the back-end of the system—the portion I was responsible for developing—which focuses on digitizing and optimizing the management of In Vitro Embryo Production (PIVE) in cattle. By shifting traditional manual processes to a centralized digital system, BovInA enhances the organization, monitoring, and efficiency of crucial steps in the PIVE process, such as oocyte collection, in vitro fertilization, and embryo cultivation. The ultimate goal is to provide a more effective tool for managing the in vitro production of bovine embryos, contributing to genetic improvement and advancing reproductive efficiency in the livestock sector.
## Main features
- **Animal Management**: Registration and tracking of donors, receivers, and bulls.
- **Oocyte Collection**: Recording collections and monitoring the number of total and viable oocytes.
- **Embryo Production**: Monitoring success rates in the production of viable embryos.
- **Pregnancy**: Tracking the success of pregnancies from productions.
- **Advanced Filters**: In-depth data analysis with filters such as:
  - Efficiency in the production of viable embryos by animal.
  - Average number of viable oocytes collected per donor.
  - Best combination of bull and donor.

## Technologies used in the system's back-end
- **Java**: Primary programming language for the system.
- **Spring Boot**:  Framework used for building web applications, including the RESTful API layer.
- **MySQL**: Database for storing and managing data.
- **Swagger**: Tool for API documentation to simplify integration between the backend and frontend, and to assist in system maintenance.
- **AWS**: Deployment of the backend using EC2 service, with the MySQL database hosted on RDS.

## System Architecture
![System Architecture](https://i.imgur.com/FYP72wJ.png)

The system architecture is organized in layers for improved scalability and maintenance:

1. **Front-end** (HTML / CSS / JavaScript / React / React Native)
   - A user-friendly interface designed for data input and visualization.
   - Communicates with the backend through a REST API.

2. **Back-end** (Java / Spring Boot)
   - **Controllers**: Handles request validation and management.
   - **Services**: Business logic and data processing.
   - **Repositories**: Database interaction using JPA/Hibernate.
   - **MySQL Database**: Structured storage for managing system data.

## Quickstart

### Prerequisites
- **Java 21+** (the project compiles to bytecode level 21; newer JDKs work as a build tool).
- **Maven Wrapper** (`./mvnw` — already in the repo; no separate Maven install needed).
- **MySQL 8+** running locally on `localhost:3306` (the `docker/docker-compose.yml` in this repo brings one up).

### Build
```bash
./mvnw clean package
```
Produces `target/pive-0.0.1-SNAPSHOT.jar`.

### Run (defaults, all env vars unset)
```bash
./mvnw spring-boot:run
```
Connects to `localhost:3306/pivedatabase` as `root` / `123`. The dev defaults match the `docker-compose.yml` MySQL container.

### Verify
- `GET http://localhost:8080/actuator/health` → `{"status":"UP"}`
- `http://localhost:8080/swagger-ui.html` → interactive API docs.

## Environment variables

All per-environment configuration is overridable via env vars. Defaults are dev-friendly; production deployments are expected to set every value explicitly.

### Database
| Variable | Default | Description |
|---|---|---|
| `DB_URL` | `jdbc:mysql://localhost:3306/pivedatabase` | JDBC URL. |
| `DB_USERNAME` | `root` | DB username. |
| `DB_PASSWORD` | `123` | DB password. |

### JPA / Hibernate
| Variable | Default | Description |
|---|---|---|
| `JPA_DDL_AUTO` | `update` | Hibernate schema-management mode (`update`, `validate`, `none`, `create`, `create-drop`). Production should run `validate` (after a schema baseline) or `none`. |
| `JPA_SHOW_SQL` | `true` | Log every SQL statement Hibernate emits. Set `false` in production. |

### API / CORS
| Variable | Default | Description |
|---|---|---|
| `BOVINA_CORS_ORIGINS` | `http://localhost:5173` | Comma-separated list of allowed CORS origins. Set to your frontend's deployed URL(s) in production. |

### Error responses
| Property (Java system property or `application.properties`) | Default | Description |
|---|---|---|
| `bovina.error.legacy-format` | `true` | When `true`, error responses use the legacy raw-string body. When `false`, error responses are RFC 7807 `ProblemDetail` JSON. The `dev` Spring profile sets it to `false`. |

### Spring profiles
- **(no profile)** — default. Legacy raw-string error bodies, full SQL logging.
- **`dev`** — activates `application-dev.properties`, which flips `bovina.error.legacy-format=false` to surface the structured-error JSON.

Activate a profile via `--spring.profiles.active=dev` or `SPRING_PROFILES_ACTIVE=dev`.

## Docker (local development)

> ⚠️ The Docker setup in this repository is for **local development and manual API testing**, not for production deployment. It ships dev-friendly defaults (root MySQL user, plaintext password in `compose.yaml`, no TLS). Production deployments should use a real secret manager and a least-privileged DB user.

### Prerequisites
- Docker 24+ with the Compose v2 plugin (`docker compose`, not the legacy `docker-compose`).
- No need to install Java, Maven, or MySQL locally for the Docker workflow.

### Run the full stack
From the repo root:
```bash
docker compose up --build
```
This builds the API image, starts MySQL on a healthy state, then starts the API. The first run downloads images and Maven dependencies and takes 5–10 minutes; subsequent runs are seconds.

Once up:
- API: `http://localhost:8080`
- Health: `http://localhost:8080/actuator/health` → `{"status":"UP"}`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

### Run only the database
If you prefer to run the API from your IDE via `./mvnw spring-boot:run`, start just MySQL:
```bash
docker compose up mysql
```
MySQL listens on `localhost:3306` of your host, so the IDE's default `DB_URL=jdbc:mysql://localhost:3306/pivedatabase` connects to it directly.

### Rebuild the API image
After a source change, force a rebuild of the API:
```bash
docker compose build api
docker compose up
```
Or, in one step:
```bash
docker compose up --build
```

### Stop containers
```bash
docker compose down
```
Containers are removed; the MySQL volume is preserved (your data survives).

### Reset the local database
```bash
docker compose down -v
```
The `-v` flag also removes the named volume `bovina-mysql-data`. The next `docker compose up` starts with a fresh, empty schema.

### Ports
| Service | Container port | Host port |
|---|---|---|
| `api` | 8080 | 8080 |
| `mysql` | 3306 | 3306 |

If you already have a process listening on 8080 or 3306, `docker compose up` will fail with `port is already allocated`. Stop the competing process or edit the `ports:` mapping in `compose.yaml` to use a different host port (e.g. `"8081:8080"`).

### Environment variables honored by the API container
The API container reads the same env vars as the host-side `./mvnw spring-boot:run` workflow — see the [Environment variables](#environment-variables) section above. The Compose file sets:

| Env var | Compose value | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:mysql://mysql:3306/pivedatabase` | DB hostname is the Compose service name `mysql`, **not** `localhost` (a container's `localhost` is itself). |
| `DB_USERNAME` | `root` | Dev-friendly default; matches the MySQL container's `MYSQL_ROOT_PASSWORD`. |
| `DB_PASSWORD` | `123` | Local-dev only. |
| `SPRING_PROFILES_ACTIVE` | `dev` | Activates `application-dev.properties` (structured-error JSON). |
| `BOVINA_CORS_ORIGINS` | `http://localhost:5173` | CORS for the Vite dev server on the host. |

Override any of them by editing `compose.yaml` or, for one-off runs, with `docker compose run -e VAR=value api`.

### Troubleshooting
- **`Bind for 0.0.0.0:8080 failed: port is already allocated`** — another container or process is using port 8080. Stop it (`docker stop <other-container>`) or change the host port in `compose.yaml`.
- **First build is slow** — Maven downloads the dependency tree on first run. Subsequent builds reuse the cached layer as long as `pom.xml` is unchanged.
- **API can't connect to MySQL** — inside the API container, the database hostname is `mysql` (the Compose service name), never `localhost`. The `compose.yaml` sets `DB_URL` accordingly.

### What this Docker setup does NOT cover
- No TLS / HTTPS termination.
- No production-grade secrets management.
- No replicated or backed-up MySQL.
- No image vulnerability scanning, SBOM, or signature verification.
- No log shipping or metrics aggregation.
- No graceful rolling deployment.

All of those are production-deployment concerns and live elsewhere (out of this repository's scope).


