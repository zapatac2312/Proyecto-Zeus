# 🏛️ Proyecto Zeus

## Descripción
El proyecto Zeus es un microservicio desarrollado con Java 17 y Spring Boot. Se trata de una aplicación de gestión de gimnasio que permite gestionar los datos de los entrenadores y aprendices.

### Diagramas de Funcionamiento
Aquí se presentan tres diagramas que explican el funcionamiento del microservicio:

1. Diagrama de Arquitectura
   ![Arquitectura](/path/to/architecture_diagram.png)

2. Diagrama de Clases
   ![Clases](/path/to/class_diagram.png)

3. Diagrama de Flujo
   ![Flujo](/path/to/flow_diagram.png)

## Dependencias Utilizadas
- Spring Data JPA: Para la persistencia de datos.
- WebFlux: Para la comunicación con el segundo microservicio.
- MySQL Driver: Para la conexión con la base de datos principal en MySQL.
- SpringDoc OpenAPI: Para la documentación con Swagger.
- MapStruct: Para el mapeo eficiente de datos.
- Spring Web: Para la construcción de la API REST.

## Conexión con el Segundo Microservicio
El segundo microservicio, también desarrollado con Java 17 y Spring Boot, se encarga únicamente de guardar los reportes de entrenamiento de los usuarios en una base de datos no relacional con MongoDB. La conexión con este segundo microservicio se realiza a través de WebFlux.

## Ejemplos de Solicitudes

### Añadir un Aprendiz
**URL:** `https://http:/localhost:8080/trainee/add`

**Input:**
```json
{
    "name": "Trainee 1",
    "email": "trainee1@example.com",
    "password": "password1",
    "dateOfBirth": "2000-01-01",
    "gender": "Male",
    "trainingGoal": "Build muscle",
    "fitnessLevel": "Intermediate"
}
