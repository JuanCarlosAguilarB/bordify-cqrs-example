package com.bordify.userdetail.application.create;

import com.bordify.auth.domain.DuplicateEmailException;
import com.bordify.userdetail.domain.User;
import com.bordify.userdetail.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Service class for user-related operations.
 */
@Service
@AllArgsConstructor
public class UserCreator {

    private final UserRepository userRepository;

    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @param user The user object containing the user details to be created.
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(User user) {

        userRepository.save(user);

    }

}
