package com.bordify.userdetails.application.find;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetails.domain.User;
import com.bordify.userdetails.domain.UserNotFoundException;
import com.bordify.userdetails.domain.UserRepository;
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
     * @return The User object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    public PageResult<User> getall(PaginationRequest pagination) {

        return userRepository.findAll(pagination);

    }

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

}