Markdown
# 🚀 Spring Boot 3 Boilerplate Starter Kit (JWT & Role-Based Security)

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

[Español](#español) | [English](#english)

---

<a name="español"></a>
## 🇦🇷 Descripción en Español

**startedKit** es una plantilla base (Boilerplate) profesional, robusta y escalable desarrollada en **Java 17+** y **Spring Boot 3**. Diseñada para acelerar el desarrollo de proyectos backend de nivel comercial, incluye autenticación sin estado mediante **JWT**, control de acceso basado en roles (RBAC), manejo global de excepciones estandarizado y documentación interactiva con **Swagger / OpenAPI**.

### 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Framework Backend:** Spring Boot 3
* **Seguridad:** Spring Security, JSON Web Token (JWT)
* **Persistencia:** Spring Data JPA, H2 Database (Desarrollo), compatible con MySQL/PostgreSQL (Producción)
* **Documentación API:** Springdoc OpenAPI / Swagger UI
* **Herramientas & DTOs:** Lombok, Maven

### ✨ Características Principales

1. **Autenticación & Autorización Stateless:**
   * Login y Registro securizados mediante JWT.
   * Roles preconfigurados: `ROLE_ADMIN` y `ROLE_USER`.
   * Protección granular por endpoints utilizando la anotación `@PreAuthorize`.

2. **Manejador Global de Excepciones:**
   * Intercepción uniforme de errores mediante `@RestControllerAdvice`.
   * Formato de respuesta JSON estandarizado para credenciales inválidas (`401`), accesos denegados (`403`) y errores de validación `@Valid` (`400`).

3. **Paginación & Ordenamiento Dinámico:**
   * DTO envoltorio genérico `PageResponse<T>` para listas optimizadas.
   * Consultas paginadas por tamaño, número de página, criterio de ordenación y búsqueda.

4. **Configuración CORS Global:**
   * Habilitado para integración inmediata con frontends en React, Angular, Vue o aplicaciones móviles.

5. **Documentación Interactiva integrada:**
   * Swagger UI configurado con soporte para el header `Authorization: Bearer <JWT>`.

---

### 🚀 Guía de Inicio Rápido

#### 1. Clonar el Repositorio
```bash
git clone [https://github.com/davidrc81/spring-boot-starter-kit.git](https://github.com/davidrc81/spring-boot-starter-kit.git)
cd spring-boot-starter-kit
2. Ejecutar la Aplicación
Bash
mvn spring-boot:run
3. Acceso a Swagger UI y H2 Console
Swagger UI: http://localhost:8080/swagger-ui.html

Consola H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:startedkitdb

User: sa

Password: (en blanco)

🇬🇧 English Description
startedKit is a professional, robust, and scalable backend boilerplate built with Java 17+ and Spring Boot 3. Designed to accelerate the creation of commercial-grade RESTful APIs, it features stateless JWT authentication, Role-Based Access Control (RBAC), standardized global exception handling, and interactive API documentation powered by OpenAPI / Swagger UI.

🛠️ Tech Stack
Language: Java 17+

Core Framework: Spring Boot 3

Security: Spring Security, JSON Web Tokens (JWT)

Persistence: Spring Data JPA, H2 Database (Development), MySQL/PostgreSQL ready (Production)

API Documentation: Springdoc OpenAPI / Swagger UI

Utilities & DTOs: Lombok, Maven

✨ Key Features
Stateless Auth & Authorization:

Secure Login and Registration via JWT tokens.

Pre-configured roles: ROLE_ADMIN and ROLE_USER.

Fine-grained method security using @PreAuthorize.

Global Exception Handling:

Intercepted exceptions via @RestControllerAdvice.

Unified JSON error payloads for unauthorized access (401), forbidden actions (403), and @Valid request body errors (400).

Dynamic Pagination & Sorting:

Generic PageResponse<T> wrapper for optimized API data streaming.

Search, sorting, and pagination options available out of the box.

Global CORS Configuration:

Fully configured to support seamless frontend integration (React, Vue, Angular) and mobile apps.

Interactive Swagger Documentation:

Integrated OpenAPI interface with Bearer Token Authorization support.

🚀 Quick Start Guide
1. Clone Repository
Bash
git clone [https://github.com/davidrc81/spring-boot-starter-kit.git](https://github.com/davidrc81/spring-boot-starter-kit.git)
cd spring-boot-starter-kit
2. Run Application
Bash
mvn spring-boot:run
3. Access Swagger UI & H2 Console
Swagger UI: http://localhost:8080/swagger-ui.html

H2 Console: http://localhost:8080/h2-console

👨‍💻 Author
Developed by David Coria

GitHub: @davidrc81
