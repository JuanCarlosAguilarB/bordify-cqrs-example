package com.bordify.auth.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthInformationRepositoryJpa extends JpaRepository<UserAuthInformationEntity, UUID> {

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
    public Optional<UserAuthInformationEntity> findByUserName(String userName);

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
    public Optional<UserAuthInformationEntity> findByEmail(String email);

}
