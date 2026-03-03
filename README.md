# Restaurante

Aplicación de gestión para restaurante desarrollada en Java con Spring Boot y JSF.

## Tecnologías Utilizadas
- **Java**
- **Maven**: Para la gestión del ciclo de vida del proyecto.
- **Spring Boot**: Framework principal para el backend.
- **JSF (JavaServer Faces)**: Utilizado para la capa de vista (`.xhtml`).
- **JPA/Hibernate**: Mapeo objeto-relacional para la persistencia de datos.

## Estructura del Proyecto
El proyecto sigue una arquitectura organizada en capas:
- `controller/`: Manejadores de las peticiones (ej. `PedidoController`).
- `model/`: Entidades de la base de datos (ej. `Plato`, `Cliente`).
- `repository/`: Capa de acceso a datos.
- `service/`: Lógica de negocio (interfaces e implementaciones).
- `view/`: Beans de respaldo para la interfaz de usuario.
