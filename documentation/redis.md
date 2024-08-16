### Redis Configuration
## Dependencies
First, make sure you have the following dependencies in your **pom.xml** file if you use **Maven**:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>3.3.2</version>
</dependency>
```

If you use **Gradle**, add the following dependencies to your **build.gradle** file:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-redis:3.3.2'
```

## Configuration
Next, configure the Redis connection in your **application.properties** file:

```properties
spring.redis.host=localhost
spring.redis.port=6379
```

Replace **localhost** with the hostname or IP address of your Redis server, and **6379** with the port number of your Redis server.

## Usage
Now, you can use Redis in your Spring Boot application. For example, you can create a Redis repository using CrudRepository:

```java
public interface UserRepository extends CrudRepository<User, String> {
}
```
We **should** use **CrudRepository** instead of JpaRepository if we want to use Redis with JPA, **not use JpaRepository** because it does not support Redis.

Now you can use the repository to save, retrieve, and delete objects of type User.

Now, in the **User** **entity**, we have to add the **@RedisHash** annotation to the class, and we need to add the **@Id** annotation to the userId field. 
> we need import @Id from org.springframework.data.annotation.Id not from jakarta.persistence.Id (this is for JPA entities not for Redis)

```java
@RedisHash
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;

    // getters and setters
}
```
You can retrieve a user from Redis:

```java
User user = userRepository.findById("john").get();
```

You can delete a user from Redis:

```java
userRepository.deleteById("john");
```

# Notes on redis:

- If we want to search for a user by their username or email, we need to use the **findByUserName** or **findByEmail** method and index the fields **userName** and **email** in the **UserEntity** class.
 ```java
@RedisHash
public class UserEntity {

    @Id
    private UUID userId;
    @Indexed
    private String email;
    @Indexed
    private String userName;
    private String password;
    private Boolean isVerified;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
}
