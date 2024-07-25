package com.bordify.user.domain;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;

import java.util.Optional;
import java.util.UUID;


/**
 * Repository interface for accessing and managing user entities in the database.
 */
public interface UserRepository {


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
    public Optional<User> findByUsername(String username);

    /**
     * Saves a user entity to the database.
     *
     * @param user The user entity to be saved.
     */
    public void save(User user);


    /**
     * Retrive an user
     *
     * @param userId
     * @return
     */
    public Optional<User> findById(UUID userId);

    PageResult<User> findAll(PaginationRequest pagination);

    void delete(UUID id);
}