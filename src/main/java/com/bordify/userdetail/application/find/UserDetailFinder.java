package com.bordify.userdetail.application.find;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserDetailRepository;
import com.bordify.userdetail.domain.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailFinder {


    private final UserDetailRepository userDetailRepository;

    public UserDetailFinder(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    /**
     * Retrieves a user by the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserDetail object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public UserDetail findUserByUsername(String username) {
        return userDetailRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with username: " + username));
    }

    public PageResult<UserDetail> getall(PaginationRequest pagination) {

        return userDetailRepository.findAll(pagination);

    }

    public UserDetail findUserById(UUID id) {
        return userDetailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with id: " + id));
    }

}