# Bordify

### Description

Bordify is a monolithic Trello-style application designed to manage tasks and projects efficiently. It is built using
Spring Boot and PostgreSQL, and offers Docker support, thus facilitating its deployment and scalability. This project
serves as a basis for future refactoring and as an example of monolithic architecture in modern applications.

### Features

- User authentication with JWT
- Project and task management.
- CRUDs
- Projections and DTOs
- Spring Boot
- PostgreSQL database.
- Redis
- Swagger
- Docker and Docker Compose support
- Monolith built with Spring Boot.

* Added CI/CD pipeline with GitHub Actions for automated testing and deployment. 👌

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

### Database

Diagram of tables.

![diagram database](/images/diagram_db.png)

### Installation

- **Local configuration without Docker**

1. Clone the repository:

```bash
git clone git@github.com:JuanCarlosAguilarB/bordify-monolith.git
```

2. Navigate to the project directory:

```bash
cd bordify-monolith
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

