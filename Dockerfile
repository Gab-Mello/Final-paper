# syntax=docker/dockerfile:1.7
#
# BovInA backend — local development image.
#
# Multi-stage build:
#   - `build`   compiles the fat JAR from source using the Maven Wrapper.
#   - `runtime` ships only the JAR on a JRE-only Alpine base for a small final image.
#
# This image is intended for local development and manual API testing via
# `docker compose up --build`. It is NOT a production deployment artifact:
# it ships dev-friendly defaults, runs Maven Wrapper at build time (so the
# image carries the build toolchain logs in its layer history), and trusts
# the environment variables set by Compose. For production, expect a separate
# build pipeline that produces a smaller, attested image.

############################
# Stage 1: build the JAR
############################
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Copy the Maven Wrapper first so its layer is reused across source-only changes.
COPY mvnw mvnw
COPY .mvn .mvn
RUN chmod +x mvnw

# Resolve dependencies in a separate, cacheable layer. As long as pom.xml does
# not change, this layer is reused on subsequent rebuilds even when source
# files change -- saving multiple minutes of Maven downloads.
COPY pom.xml pom.xml
RUN ./mvnw -B -ntp dependency:go-offline

# Build the application.
COPY src src
RUN ./mvnw -B -ntp -DskipTests package

############################
# Stage 2: runtime image
############################
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Run as a non-root user (least privilege). uid:gid 1000:1000 is the
# conventional first non-system user; Alpine's `addgroup`/`adduser` differ
# slightly from Debian's, so we use BusyBox-compatible flags.
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the fat JAR. The pom's <artifactId>-<version> resolves to
# pive-0.0.1-SNAPSHOT.jar by default.
COPY --from=build /workspace/target/pive-*.jar /app/app.jar
RUN chown spring:spring /app/app.jar

USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
