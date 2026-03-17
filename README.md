# RedFlag API

Spring Boot REST API with JWT security, PostgreSQL persistence, and Flyway migrations. Includes Swagger UI (OpenAPI) for interactive API docs.

## Tech Stack

- Java 21
- Spring Boot 4 (Web MVC, Validation)
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Flyway
- springdoc-openapi (Swagger UI)
- Docker / Docker Compose
- Render (deployment)

## Features

- Auth: register + login (JWT)
- Role-based authorization (ADMIN / MODERATOR / REPORTER)
- CRUD: Categories and Reports
- Search/filter Reports (query params)
- Automatic database migrations on startup (Flyway)
- Swagger UI + OpenAPI schema

## Demo (Swagger UI)

- Swagger UI: `https://redflag-trss.onrender.com/swagger-ui.html`
- OpenAPI JSON: `https://redflag-trss.onrender.com/v3/api-docs`

Note: visiting the root URL `/` returns `401` by design; only `/auth/**` and Swagger/OpenAPI endpoints are public.

## Run Locally (Docker Compose)

1. Create a `.env` file:

```env
DB_USERNAME=postgres
DB_PASSWORD=change_me
```

2. Start:

```powershell
docker compose up -d --build
```

3. Open:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

