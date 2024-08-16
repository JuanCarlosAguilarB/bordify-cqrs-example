package com.bordify.auth.domain;


import java.util.Optional;

public interface UserReadModelRepository {


    /**
     * Saves a user entity to the database.
     *
     * @param user The user to be saved.
     */
    public void save(UserReadModel user);

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.
     */
    public Optional<UserReadModel> findByUsername(UserUserName username);

}
