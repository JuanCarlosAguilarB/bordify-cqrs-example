# Bordify

### Description

Bordify is a Trello-style application designed to manage tasks and projects efficiently. Now,
this project is an example of CQRS (Command Query Responsibility Segregation) architecture implementation in a
Spring Boot based system. The objective is to separate read and write operations on the database to improve the scalability and efficiency of the system.

### Architecture and Patterns

#### CQRS Implementation
In this project, CQRS is implemented by separating commands and queries

- **Commands**: commands are responsible for write operations. They have been implemented in services such as BoardCreator, BoardUpdater, and BoardDeleter, which manage the creation, update, and deletion of entities.
- **Queries**: Read operations are separated and would typically be handled in dedicated services, such as BoardFinder. This ensures that read and write operations can be scaled and optimized independently.
### Features

- **CQRS Architecture**: Implementation of CQRS with separation of commands and queries to improve scalability and efficiency.
- **CRUD Operations**: Management of entities such as Board and TaskItem with services to create, update, read and delete - data.
- **Spring Boot Framework**: Backend development using Spring Boot.
- **PostgreSQL Database**: Data persistence in a PostgreSQL database.
- **Redis Database**: Use of Redis as a database for caching and accelerating data access.
- **Custom Exception Handling**: Handling of specific exceptions such as EntityNotFound and ResourceNotCreatedException.
- **Repository Pattern**: Use of repositories for data access abstraction and persistence.
- **REST API**: REST controllers to manage CRUD operations and provide endpoints to clients.
- **Swagger Documentation**: Automatic API documentation using Swagger.
- **Docker Support**: Docker support for containerized project deployment and execution.
- **Test Coverage**: Test coverage with unit and integration tests.

### Project Structure

- **bordify-api**: Spring Boot application that serves as the backend for the Bordify application.

```plaintext
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com.bordify/
    │   │       ├── models/
    │   │       ├── services/
    │   │       ├── repositories/
    │   │       ├── dtos/
    │   │       ├── config/
    │   │       ├── utils/
    │   │       ├── controllers/
    │   │       └── BordifyApplication.java
    │   └── resources/
    │       ├── static
    │       └── templates
    └── test/
        └── java/
            └── com.bordify/
                └── BordifyApplicationTest.java
```

- **New Architecture**:
The project structure has evolved to support a CQRS implementation and possibly other architectural enhancements. For Example:

```plaintext
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com.bordify/
    │   │       ├── board/
    │   │       │   ├── application/
    │   │       │   │   ├── create/
    │   │       │   │   ├── find/
    │   │       │   │   ├── update/
    │   │       │   │   └── delete/
    │   │       │   ├── domain/
    │   │       │   └── infrastructure/
    │   │       │       ├── persistence/
    │   │       │       └── controllers/
    │   │       ├── task_item/
    │   │       │   ├── application/
    │   │       │   ├── domain/
    │   │       │   └── infrastructure/
    │   │       │       ├── persistence/
    │   │       │       └── controllers/
    │   │       ├── configuration/
    │   │       │   └── infrastructure/
    │   │       └── BordifyApplication.java
    │   └── resources/
    │       ├── static/
    │       └── templates/
    └── test/
        └── java/
            └── com.bordify/
                ├── board/
                ├── task_item/
                └── BordifyApplicationTest.java

```

### Database

Initial database diagram:

![diagram database](/images/diagram_db.png)

### Installation

- **Local configuration without Docker**

1. Clone the repository:

```bash
git clone git@github.com:JuanCarlosAguilarB/bordify-cqrs-example.git
```

2. Navigate to the project directory:

```bash
cd bordify-cqrs-example
```

3. Set the required environment variables for the database and any other necessary settings
   in`src/main/resources/application.properties.`
4. Run the application:

```bash
./gradlew bootRun
```

* **Using Docker Compose**

1. Ensure that Docker and Docker Compose are installed on your machine.
2. Execute the following command to build the Docker images and run the containers:

```bash
docker-compose up
```
* **Access the application at `http://localhost:8080`.**

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Notes on redis:

In this project, JPA (Java Persistence API) has been used for data management in the database. Although Redis is not a relational database, the integration with JPA allows easy management of cached data.

For the connection to Redis, Lettuce was used as a client. Lettuce is a client library for Redis that integrates seamlessly with Spring Boot. The following explains why Lettuce was chosen instead of Jedis:

### Lettuce vs Jedis
- Connection Handling Efficiency: Lettuce uses an asynchronous, Netty-based model, which allows it to handle multiple connections efficiently and without blocking. This is ideal for applications with high concurrency.

- Scalability: Lettuce is more scalable than Jedis due to its ability to handle multiple threads and parallel connections without blocking. This improves performance in applications that require high availability and speed.

- Compatibility: Lettuce is the client recommended by Spring Data Redis to integrate with Spring Boot due to its support for asynchronous connections and its event-driven architecture.

- Stateless: Lettuce does not require the client to maintain connection state, which simplifies resource management and improves application efficiency.

## Implementing Redis

To use **Redis** in a **Spring Boot application**, see the steps in the following documentation.

-  [Implementing Redis](documentation/redis.md)

