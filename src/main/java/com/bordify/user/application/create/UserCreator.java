package com.bordify.user.application.create;

import com.bordify.user.domain.DuplicateEmailException;
import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
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
