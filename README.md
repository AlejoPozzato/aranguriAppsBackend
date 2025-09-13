# ⚙️ Backend - Gestor de Materias

## 📝 Introducción
Backend de la aplicación de gestión de materias, profesores, fechas de examen y archivos asociados.  
Los ejemplos de uso y flujos de usuario están disponibles en el [frontend](https://github.com/AlejoPozzato/aranguriapps-frontend).

---

## 🛠️ Tecnologías Utilizadas y Justificación

- **Java + Spring Boot** – Elegí Spring Boot por su facilidad para construir APIs REST robustas y escalables, con mínima configuración y gran soporte para proyectos empresariales.
- **Spring Security** – Para manejar autenticación y autorización de manera segura, integrando JWT para login sin estado y protección de endpoints.
- **JPA (Java Persistence API)** – Elegí JPA para la persistencia de datos en PostgreSQL, simplificando el manejo de entidades y consultas mediante repositorios.
- **JWT (JSON Web Token)** – Permite sesiones seguras sin depender de cookies de sesión.
- **PostgreSQL** – Base de datos relacional confiable y ampliamente usada; la levanté en un contenedor Docker para mantener el entorno aislado y reproducible.
- **Maven** – Para gestión de dependencias, compilación y empaquetado, lo que permite reproducibilidad y un flujo de build estándar en Java.

---

## 🏛️ Arquitectura y Estructura del Proyecto

La aplicación sigue una **arquitectura en capas**, que permite separar responsabilidades y mantener código modular:

- **Model** – Contiene las entidades que representan las tablas de la base de datos. Define la estructura de los datos y sus relaciones.
- **Controller** – Expone los endpoints REST. Aquí se reciben las solicitudes HTTP y se delega la lógica a los servicios.
- **Service** – Implementa la lógica de negocio, validaciones y coordinación de acciones entre controller y repositorio.
- **Repository** – Maneja la persistencia de entidades usando JPA, facilitando operaciones CRUD sin necesidad de escribir SQL manual.
- **DTO (Data Transfer Object)** – Objetos que permiten transferir datos entre backend y frontend de forma segura, evitando exponer entidades directamente.

**Justificación:** Esta separación hace que el código sea más mantenible, testeable y escalable. Permite modificar la lógica de negocio sin afectar los endpoints y facilita la integración de nuevas funcionalidades. Originalmente consideré implementar una arquitectura limpia (Clean Architecture) para desacoplar completamente la lógica de negocio de la infraestructura y aumentar la testabilidad. Sin embargo, dado que este proyecto es relativamente pequeño y con un alcance acotado, opté por una arquitectura en capas más simple (Controller → Service → Repository → Model → DTO).

---

## ⚙️ Instalación y Ejecución

### ✅ Requisitos Previos
- Tener instalado **Java 21+** y **Maven 3+**
- Tener instalado **Docker + Docker Compose**

### 💻 Correr Localmente con Docker

1. Levantar la base de datos PostgreSQL:

docker-compose up -d db

2. Construir y ejecutar la aplicación con Maeven:

./mvnw spring-boot:run

La aplicación correrá en: http://localhost:8080

