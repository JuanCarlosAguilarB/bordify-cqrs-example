package com.bordify.auth.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * JPA repository for the User entity.
 * if we want to use JPA with Redis, we need to use CrudRepository instead of JpaRepository
 * CrudRepository is a specialization of JpaRepository that adds additional methods for CRUD operations.
 *
 * JpaRepository does not support Redis, so we need to use CrudRepository instead.
 */

//public interface UserRepositoryJpa extends JpaRepository<UserReadModelEntity, UUID> {
public interface UserRepositoryJpa extends CrudRepository<UserReadModelEntity, UUID> {

    /**
     * Checks if a user exists with the given username.
     *
     * @param userName The username of the user.
     * @return True if a user exists with the given username, false otherwise.
     */
    public boolean existsByUserName(String userName);


    /**
     * Retrieves a user by their username.
     *
     * @param userName The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public Optional<UserReadModelEntity> findByUserName(String userName);

    /**
     * Checks if a user exists with the given email.
     *
     * @return True if a user exists with the given email, false otherwise.
     */
    public boolean existsByEmail(String email);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The user with the specified email address, or null if not found.
     */
    public Optional<UserReadModelEntity> findByEmail(String email);

}
