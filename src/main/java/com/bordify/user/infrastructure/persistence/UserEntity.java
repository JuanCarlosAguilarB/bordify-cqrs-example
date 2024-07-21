package com.bordify.user.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

/**
 * Represents a user entity in the Bordify application.
 * This class defines the structure of a user entity, including their username, password, email,
 * name, verification status, phone number, and timestamps for creation and last login.
 *
 * The `User` class leverages Lombok annotations to reduce boilerplate code, generating getters,
 * setters, constructors, and a builder pattern. Additionally, the `@Table` annotation is used
 * to specify the database table name as `"\"user\""`, which ensures compatibility with databases
 * where `user` is a reserved keyword or requires case-sensitive handling.
 */
@Entity
@Data
@Builder
@Table(name = "\"user\"")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    /**
     * Unique identifier for the User. It is automatically generated and uses UUID as the ID type.
     */
    @Id
    private UUID id;

    /**
     * First name of the user.
     */
    private String firstName;

    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Phone number of the user.
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Username of the user. This field must be unique.
     */
    @Column(unique = true)
    private String username;


}
