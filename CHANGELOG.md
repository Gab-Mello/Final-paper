# Changelog

All notable behavior-affecting changes from the BovInA backend refactor.

The format follows the spirit of [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).
Status code corrections in particular may require frontend coordination — they
are flagged with **BREAKING** below.

## [Unreleased]

This is the cumulative changeset of the refactor across Phases 0–8 of
`refactor/phase-0-and-8-*` (committed directly to `main`).

### Added
- Spring Boot Actuator dependency exposing `GET /actuator/health` plus the
  Kubernetes-style `liveness` and `readiness` probes.
- Graceful shutdown: `SIGTERM` waits up to 30 s for in-flight requests to
  finish before exiting.
- RFC 7807 `ProblemDetail` JSON error bodies — gated behind
  `bovina.error.legacy-format` (default `true`; `dev` profile sets it `false`).
- `BusinessException` base class; every domain exception extends it and
  declares its own `HttpStatus`.
- Cross-field Bean Validation constraint `@ViableNotExceedingTotal` on
  `OocyteCollectionRequestDto`.
- `@Valid` finally wired on `TransferController`, `ScheduleController`, and
  `PregnancyController` POST/PUT endpoints.
- `@Positive` / `@PositiveOrZero` on count fields across request DTOs
  (`OocyteCollectionRequestDto`, `ProductionRequestDto`, `FrozenEmbryosDto`,
  `DiscardedEmbryosDto`).
- `@NotBlank` on `BullDto.name`.
- `@NotNull` on previously-unvalidated DTO fields:
  `ScheduleRequestDto.{procedureType,date}`, `TransferInitialDto.fivId`,
  `PregnancyRequestDto.receiverCattleId`, `ProductionRequestDto.oocyteCollectionId`.
- `@FutureOrPresent` on `ScheduleRequestDto.date`.
- `PregnancyResponseDto` (new), replacing the raw `Pregnancy` entity
  previously exposed via `ReceiverCattleDto`.
- `application-dev.properties` enabling structured-error JSON in the `dev`
  profile.

### Changed (BREAKING — coordinate with frontend)
- `OocyteCollectionNotFoundException`: status `409 Conflict` → **`404 Not Found`**.
- `InvalidNumberOfEmbryosException`: status `404 Not Found` → **`400 Bad Request`**.
- `MethodArgumentNotValidException` (`@Valid` failures): status `409 Conflict`
  → **`400 Bad Request`**, with every field error aggregated into the body
  (was: only the first error).
- `ViableOocytesBiggerThanTotalException`: thrown only by the
  `@ViableNotExceedingTotal` validator → status `409` → **`400 Bad Request`**.
- `POST /transfer` response body: was `TransferInitialDto`, is now
  **`TransferResponseDto`** (adds `id` and `embryos` fields).

### Changed (BREAKING — bad-input cases)
The following requests previously succeeded silently or returned `500` and now
return `400 Bad Request`:
- `POST /bull` with blank `name`.
- `POST /oocyte-collection` with negative `totalOocytes` / `viableOocytes` or
  with `viableOocytes > totalOocytes`.
- `POST /production` with missing `oocyteCollectionId` or `totalEmbryos <= 0`.
- `POST /transfer` with missing `fivId`.
- `POST /embryo/frozen` or `/discarded` with `embryosQuantity <= 0`.
- `POST /pregnancy` with missing `receiverCattleId`.
- `POST /schedule` with missing `procedureType` / `date` or a `date` in the
  past.

### Changed (non-breaking)
- DB credentials, JDBC URL, CORS origin, and JPA flags now overridable via
  env vars (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `BOVINA_CORS_ORIGINS`,
  `JPA_DDL_AUTO`, `JPA_SHOW_SQL`). Defaults preserve the previous dev behavior.
- CORS allowed origin default: `http://localhost:5173/` → `http://localhost:5173`
  (dropped the spurious trailing slash that intermittently broke preflight
  responses on some browsers).
- `Fiv` status now transitions to `COMPLETED` exactly when
  `embryosRegistered == totalEmbryos` (was: `== totalEmbryos - 1`,
  one transfer too early).
- SSE notifications now use `CopyOnWriteArrayList` for emitter state; SLF4J
  logging replaces `System.out.println`. Schedules flip to `SUCCESS` only when
  at least one client actually received the event (was: flipped on any send;
  zero-client case left them PENDING silently).
- All write-path service methods are now `@Transactional` — multi-aggregate
  writes (production, transfer, oocyte collection, pregnancy confirmation)
  are now atomic.
- `DonorCattle.oocyteCollections` fetch type: `EAGER` → `LAZY`. Donor list
  queries no longer eager-load children.
- `ReceiverCattleDto.pregnancy` field type: raw `Pregnancy` entity →
  `PregnancyResponseDto`. Wire JSON shape is identical.
- `GlobalExceptionHandler` shrank from 20 `@ExceptionHandler` methods to 4
  (`BusinessException`, `MethodArgumentNotValidException`,
  `EntityNotFoundException`, generic `Exception`).
- `PregnancyRequestDto`: Java field `is_pregnant` → `pregnant`. Wire JSON
  key preserved as `is_pregnant` via `@JsonProperty`.

### Fixed
- `FivService.checkToSetFivAsCompleted`: off-by-one (`== total - 1`) bug
  preventing the final embryo from registering on some flows.
- `PregnancyService.updateGestationalAge`: silent no-op because the
  scheduled method had no transaction; dirty-check now flushes the daily
  update.
- `PregnancyService.updatePregnancyData` and `FivService.updateTotalEmbryos`:
  div-by-zero guards (returned `Infinity` / `NaN` in corner cases; now
  return `0.0`).
- `DonorCattle.updateEmbryoPercentage`: NPE on collections without a
  production; also fixed an incorrect denominator (was: all collections;
  now: collections with a production).
- `ReceiverCattleService.delete`: NPE when deleting a receiver without
  an embryo; now safely returns 204.
- `DonorBullCombinationService.getBestDonorBullCombinations`: sort key was
  re-parsing a formatted percentage string (locale-dependent, precision-losing);
  now sorts on the raw double.
- `GlobalExceptionHandler`: duplicate method name (`embryoNotFound` for two
  different exception types). Renamed.

### Removed
- 51 MB stray JAR file (`ec2-user@...amazonaws.com`) accidentally committed
  at the repo root.
- Dead commented-out `editCollection` block in `OocyteCollectionService`.
- Unused `@Autowired` + `FivRepository` imports from `Fiv` entity.
- Every `@Autowired` field injection across the codebase
  (`grep -rn "@Autowired" src/main/java` returns zero).

### Deferred
- Rename of `tb_schedules` table from `db_schedules` — requires Flyway
  adoption first. Documented inline on the `Schedule` entity.
- Flyway / Liquibase introduction.
- Spring Security around `/actuator/**`.
- Architectural review of `ProductionService` (currently 7 dependencies,
  orchestration-shaped).
