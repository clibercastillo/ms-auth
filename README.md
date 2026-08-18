# ms-auth

Microservicio de autenticación y autorización con Spring Boot, PostgreSQL y JWT.

## Stack

- Java 17 · Spring Boot 4.1.0
- Spring Security + JWT
- Spring Data JPA + PostgreSQL
- Swagger / OpenAPI (springdoc 3.0.3)
- Gradle
- Docker

## Requisitos

- JDK 17
- Docker (para levantar Postgres local)

## Levantar local

```bash
docker compose up -d
./gradlew bootRun
```

App disponible en `http://localhost:8080`
Swagger UI en `http://localhost:8080/swagger-ui.html`

## Endpoints principales

| Método | Ruta                | Descripción                  | Auth |
|--------|---------------------|-------------------------------|------|
| POST   | `/api/auth/register`| Registrar usuario              | No   |
| POST   | `/api/auth/login`   | Login (email + password) → JWT | No   |

Rutas protegidas requieren header:
```
Authorization: Bearer <token>
```

## Variables de entorno

| Variable                | Descripción                  |
|--------------------------|-------------------------------|
| `SPRING_DATASOURCE_URL`  | URL de conexión a PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la BD          |
| `SPRING_DATASOURCE_PASSWORD` | Password de la BD         |
| `JWT_SECRET`             | Clave secreta para firmar JWT |

## Docker

```bash
docker build -t ms-auth:local .
docker run -p 8080:8080 ms-auth:local
```

## CI/CD

El pipeline en `.github/workflows/ci.yml` compila el proyecto y publica la imagen en GitHub Container Registry (`ghcr.io`) en cada push a `main` o `develop`.
