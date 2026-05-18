# AGENTS.md

## Project snapshot
- This is a single-module Maven Java project focused on API tests for `https://jsonplaceholder.typicode.com`.
- The implementation currently lives entirely under `src/test/...`; there is no production code under `src/main/...` yet.
- Tests use **JUnit Jupiter** and **Rest Assured** with a lightweight **Service Object** pattern.

## Key files and structure
- `pom.xml` — defines Java 17, JUnit Jupiter, Rest Assured, Hamcrest, Jackson Databind, and Surefire test execution.
- `src/test/java/com/example/apitests/service/BaseApiService.java` — base service class scaffold for shared request setup.
- `src/test/java/com/example/apitests/service/UsersService.java` — `/users` service scaffold extending the base service.
- `src/test/java/com/example/apitests/model/User.java` — lightweight DTO with Jackson-friendly fields/getters for user payload mapping.
- `src/test/java/com/example/apitests/UsersApiTest.java` — placeholder test class for `/users` endpoint assertions.
- `src/test/resources/junit-platform.properties` — enables JUnit 5 parallel execution.
- `README.md` — shortest verified way to run the suite.

## Current test design
- Keep endpoint-specific request logic in small service classes under `src/test/java/.../service`.
- Keep assertions in test classes; services are currently scaffolds and are intended to return response data for test-layer validation.
- Prefer independent tests with no shared mutable state so they remain safe under parallel execution.
- Use the existing `User` model mapping style (`@JsonIgnoreProperties(ignoreUnknown = true)` with getters-only DTO fields) when deserializing JSONPlaceholder user payloads.

## Build / test workflow
- Verified from repo root:
  - `mvn test`
- Current test/service classes are scaffolds; live endpoint assertions are not implemented yet.
- Parallel execution is enabled both in Surefire system properties and in `src/test/resources/junit-platform.properties`.

## Repo-specific cautions
- Do not introduce shared static test state; the suite is configured for concurrent execution.
- Do not move the current request code into `src/main/...` unless the project grows reusable client code beyond test support.
- Do not rely on `.idea/workspace.xml`; it is user-local state and is ignored by `.idea/.gitignore`.
- If you add new endpoints, follow the existing pattern: one small service object per endpoint area, one focused test class per feature.

## Good first places to look
- `pom.xml` for dependency/plugin changes
- `src/test/java/com/example/apitests/service/` for request-building patterns
- `src/test/java/com/example/apitests/model/User.java` for JSON payload mapping conventions
- `README.md` for the simplest local execution command

