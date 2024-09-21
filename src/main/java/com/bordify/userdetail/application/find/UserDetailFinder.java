package com.bordify.userdetail.application.find;

import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetail.domain.*;
import org.springframework.stereotype.Service;


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

    public AllUsersDetailResponse getall(PaginationRequest pagination) {

        return userDetailRepository.findAll(pagination);

    }

    public UserDetailResponse findUserById(UserDetailId id) {

        UserDetail user = userDetailRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with id: " + id));

        return UserDetailResponse.builder()
                .id(user.id().value())
                .firstName(user.firstName().value())
                .lastName(user.lastName().value())
                .phoneNumber(user.phoneNumber().value())
                .build();
    }

}