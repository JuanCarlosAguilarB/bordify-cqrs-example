package com.bordify.auth.domain;


import com.bordify.shared.domain.UserUserId;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserWriteModelRepository {

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username of the user.
     * @return True if a user exists with the given username, false otherwise.
     */
    public boolean existsByUsername(UserUserName username);

    /**
     * Saves a user entity to the database.
     *
     * @param user The user to be saved.
     */
    public void save(UserWriteModel user);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public Optional<UserWriteModel> findByUsername(UserUserName username);


    /**
     * Checks if a user exists with the given email.
     *
     * @param email The email address of the user.
     * @return True if a user exists with the given email, false otherwise.
     */
    public boolean existsByEmail(UserEmail email);

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The user with the specified email address, or null if not found.
     */
    public Stream<UserWriteModel> findByEmail(UserEmail email);


    Optional<UserWriteModel> findById(UserUserId userId);
}
