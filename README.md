# SpringPhotoz

Lightweight Spring Boot service that stores simple photo metadata (id, fileName).

This repository contains a tiny example API built with Spring Boot and Maven. It demonstrates basic CRUD-style endpoints and validation using Jakarta Validation.

## Quick overview

- Model: `Photo` (fields: `id`, `fileName`) — `fileName` is validated with `@NotEmpty`.
- Main endpoints (implemented in `PhotozController`):
  - GET  /         -> returns `Hello, World`
  - GET  /photoz   -> list all photos
  - GET  /photoz/{id} -> get a photo by id (404 if not found)
  - POST /photoz   -> create a photo (body: JSON, `fileName` required)
  - DELETE /photoz/{id} -> delete a photo by id (404 if not found)

## Requirements

- Java 17+ (project uses Spring Boot 4.x)
- Maven 3.6+ (the project includes the Maven wrapper `mvnw`)

## Build

From the project root run:

```bash
# using the wrapper (recommended)
./mvnw clean package

# or with a local mvn installation
mvn clean package
```

The packaged JAR will be in `target/`.

## Run

Run the app via the wrapper:

```bash
./mvnw spring-boot:run
```

Or run the packaged JAR:

```bash
java -jar target/*.jar
```

The app will start on the default port (8080) unless overridden in `src/main/resources/application.properties` or via `SPRING_APPLICATION_JSON` / `--server.port`.

## API examples (curl)

List photos:

```bash
curl -sS http://localhost:8080/photoz | jq .
```

Get a photo by id (returns 404 if missing):

```bash
curl -i http://localhost:8080/photoz/1
```

Create a photo (fileName is required):

```bash
curl -i -X POST http://localhost:8080/photoz \
  -H "Content-Type: application/json" \
  -d '{"fileName":"example.jpg"}'
```

If `fileName` is empty or missing, the request will fail validation and the server will return a 400 Bad Request.

Delete a photo:

```bash
curl -i -X DELETE http://localhost:8080/photoz/<id>
```

## Tests

Run the project's tests with:

```bash
./mvnw test
```

## Notes & tips

- The controller uses an in-memory `Map` as a simple data store; data is not persisted across restarts.
- The default seed includes a photo with id `1` and fileName `hello.jpg`.
- Add a `LICENSE` file if you plan to publish the code.

If you want, I can add example Postman/HTTPie snippets or a minimal OpenAPI/Swagger config next.
