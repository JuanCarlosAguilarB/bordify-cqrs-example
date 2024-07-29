package com.bordify.userdetail.application.find;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserNotFoundException;
import com.bordify.userdetail.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserFinder {


    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserDetail object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public UserDetail findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with username: " + username));
    }

    public PageResult<UserDetail> getall(PaginationRequest pagination) {

        return userRepository.findAll(pagination);

    }

    public UserDetail findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with id: " + id));
    }

}