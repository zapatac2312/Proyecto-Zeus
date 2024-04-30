# 🏛️ Proyecto Zeus 🏛️

## De qué se trata?
⚡ Bienvenidos mortales a "proyecto Zeus" ⚡ "Proyecto Zeus" es una API SpringBoot hecha para gimnasios que brinda soluciones fáciles y robustas para la gestión de usuarios y entrenadores. Esta API fue construida con maven usando dependencias como: MapStruck para el mapeo eficiente de dato, MySQL driver para guardar la información de todos los usuarios en una base de datos relacional con MySQL, WebFlux para consumir una segunda API que guarda los informes en una base de datos NoSQL MongoDB, entre otras

1. Diagrama Proyecto zeus:

   ![Arquitectura](https://github.com/zapatac2312/Proyecto-Zeus/blob/master/UML%20microservicio%20principal.png)

## ⚡ Dependencias Utilizadas
- Spring Data JPA: Para la persistencia de datos.
- WebFlux: Para la comunicación con el segundo microservicio.
- MySQL Driver: Para la conexión con la base de datos principal en MySQL.
- SpringDoc OpenAPI: Para la documentación con Swagger.
- MapStruct: Para el mapeo eficiente de datos.
- Spring Web: Para la construcción de la API REST.

## 💪Conexión con el Segundo Microservicio
El segundo microservicio, también desarrollado con Java 17 y Spring Boot, se encarga únicamente de guardar los reportes de entrenamiento de los usuarios en una base de datos no relacional con MongoDB. La conexión con este segundo microservicio se realiza a través de WebFlux.

2. Diagrama Microservicio secundario:

   ![Clases](https://github.com/zapatac2312/Proyecto-Zeus/blob/master/UML%20microservicio%20secundario.png)

Aqui peudes encontrar el enlace del repositorio de ese microservicio!
https://github.com/zapatac2312/Proyecto-Zeus-Aux-microservice

## 🏛️Conexión con la base de datos principal
4. Diagrama entidad relación:
   
   ![Flujo](https://github.com/zapatac2312/Proyecto-Zeus/blob/master/Diagrama%20entidad%20relación.jpeg)

## ⚡Ejemplos de Solicitudes

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
```
### Respuesta:
```json
{
        "name": "Trainee 1",
        "email": "trainee1@example.com",
        "gender": "Male",
        "trainingGoal": "Build muscle",
        "fitnessLevel": "Intermediate"
}
```

### Mostrar informacion de un Aprendiz
**URL:** `https://http:/localhost:8080/trainee/innfo`

**Input:**
```json
"trainee1@example.com"
```

### Respuesta:
```json
{
        "name": "Trainee 1",
        "email": "trainee1@example.com",
        "gender": "Male",
        "trainingGoal": "Build muscle",
        "fitnessLevel": "Intermediate"
}
```
# 🏛️ Gracias por visitar Proyecto Zeus
