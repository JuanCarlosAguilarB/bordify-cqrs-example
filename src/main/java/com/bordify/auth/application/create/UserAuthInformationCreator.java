package com.bordify.auth.application.create;

import com.bordify.auth.domain.UserAuthInformation;
import com.bordify.auth.domain.UserAuthInformationRepository;
import com.bordify.user.domain.DuplicateEmailException;
import com.bordify.user.domain.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthInformationCreator {

    private final UserAuthInformationRepository userRepository;
    private final SecurityService securityService;


    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @param user The user object containing the user details to be created.
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(UserAuthInformation user) {

        if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateEmailException("Both the email " + user.getEmail() + " and username " + user.getUsername() + " are already registered. Please use different credentials.");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("The email " + user.getEmail() + " is already registered. Please use a different email.");
        } else if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateEmailException("The username " + user.getUsername() + " is already registered. Please use a different username.");
        }

        user.setPassword(securityService.encode(user.getPassword()));
        userRepository.save(user);

    }

}