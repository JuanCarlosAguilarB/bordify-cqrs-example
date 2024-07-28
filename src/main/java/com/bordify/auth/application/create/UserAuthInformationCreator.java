package com.bordify.auth.application.create;

import com.bordify.auth.domain.*;
import com.bordify.shared.domain.UserUserId;
import com.bordify.user.domain.DuplicateEmailException;
import com.bordify.user.domain.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserAuthInformationCreator {

    private final UserAuthInformationRepository userRepository;
    private final SecurityService securityService;


    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(UserUserId id, UserEmail email, UserUserName userName, UserPassword password) {

        ensureEmailAndUserNameAreNotRegistered(email, userName);

        String rawPassword = password.value();
        UserPassword passwordEncoded = new UserPassword(securityService.encode(rawPassword));

        UserAuthInformation user = new UserAuthInformation(
                id,
                email,
                userName,
                passwordEncoded,
                new UserIsVerified(false),
                new UserDateCreated(LocalDateTime.now()),
                new UserDateLastLogin(LocalDateTime.now())
                );

        userRepository.save(user);

    }

    private void ensureEmailAndUserNameAreNotRegistered(UserEmail email, UserUserName userName) {

        boolean isEmailRegistered = userRepository.existsByEmail(email);
        boolean isUserNameRegistered = userRepository.existsByUsername(userName);

        if (isEmailRegistered && isUserNameRegistered) {
            throw new DuplicateEmailException("Both the email " + email.value() + " and username " + userName.value() + " are already registered. Please use different credentials.");
        } else if (isEmailRegistered) {
            throw new DuplicateEmailException("The email " + email + " is already registered. Please use a different email.");
        } else if (isUserNameRegistered) {
            throw new DuplicateEmailException("The username " + userName + " is already registered. Please use a different username.");
        }
    }

}