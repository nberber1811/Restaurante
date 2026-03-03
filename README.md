# Restaurante

Aplicación de gestión para restaurante desarrollada en Java con Spring Boot y JSF.

## Tecnologías Utilizadas
- **Java**
- [cite_start]**Maven**: Para la gestión del ciclo de vida del proyecto[cite: 1, 4].
- **Spring Boot**: Framework principal para el backend.
- [cite_start]**JSF (JavaServer Faces)**: Utilizado para la capa de vista (`.xhtml`)[cite: 6].
- **JPA/Hibernate**: Mapeo objeto-relacional para la persistencia de datos.

## Estructura del Proyecto
El proyecto sigue una arquitectura organizada en capas:
- [cite_start]`controller/`: Manejadores de las peticiones (ej. `PedidoController`)[cite: 4].
- [cite_start]`model/`: Entidades de la base de datos (ej. `Plato`, `Cliente`)[cite: 4].
- [cite_start]`repository/`: Capa de acceso a datos[cite: 4].
- [cite_start]`service/`: Lógica de negocio (interfaces e implementaciones)[cite: 5].
- [cite_start]`view/`: Beans de respaldo para la interfaz de usuario[cite: 5].

## Cómo ejecutar el proyecto
Este proyecto utiliza el Maven Wrapper, por lo que no necesitas tener Maven instalado globalmente.

1. Abre una terminal en la carpeta raíz del proyecto.
2. Ejecuta el siguiente comando:
   - En Linux/Mac: `./mvnw spring-boot:run`
   - [cite_start]En Windows: `mvnw.cmd spring-boot:run` [cite: 2]
