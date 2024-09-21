package com.bordify.userdetail.domain;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;

import java.util.Optional;
import java.util.UUID;


/**
 * Repository interface for accessing and managing user entities in the database.
 */
public interface UserDetailRepository {


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
    public Optional<UserDetail> findByUsername(String username);

    /**
     * Saves a user entity to the database.
     *
     * @param user The user entity to be saved.
     */
    public void save(UserDetail user);


    /**
     * Retrive an user
     *
     * @param id value object of id
     * @return Optional<UserDetail>
     */
    public Optional<UserDetail> findById(UserDetailId id);

    AllUsersDetailResponse findAll(PaginationRequest pagination);

    void delete(UserDetailId id);
}