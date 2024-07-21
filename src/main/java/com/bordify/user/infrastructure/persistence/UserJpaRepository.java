package com.bordify.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing user entities in the database.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username of the user.
     * @return True if a user exists with the given username, false otherwise.
     */
    public boolean existsByUsername(String username);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public Optional<UserEntity> findByUsername(String username);
}