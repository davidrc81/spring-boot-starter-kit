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
