package com.bordify.auth.application.create;

import com.bordify.auth.domain.*;
import com.bordify.auth.domain.event.UserCreatedEvent;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.UserUserName;
import com.bordify.shared.domain.bus.event.EventBus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCreator {

    private final UserWriteModelRepository repository;
    private final SecurityService securityService;
    private final EventBus eventBus;


    /**
     * Creates a new user with the provided user details and generates an authentication token.
     *
     * @throws DuplicateEmailException If the provided email or username is already registered.
     */
    public void createUser(UserUserId id, UserEmail email, UserUserName userName, UserPassword password) {

//        ensureEmailAndUserNameAreNotRegistered(email, userName);

        String rawPassword = password.value();
        UserPassword passwordEncoded = new UserPassword(securityService.encode(rawPassword));

        LocalDateTime now = LocalDateTime.now();
        UserWriteModel user = new UserWriteModel(
                id,
                email,
                userName,
                passwordEncoded,
                new UserIsVerified(false),
                new UserDateCreated(now),
                new UserDateLastLogin(now)
                );

        repository.save(user);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                id.value(),
                email.value(),
                userName.value(),
                now
        );
        eventBus.publish(List.of((userCreatedEvent)));
    }

    private void ensureEmailAndUserNameAreNotRegistered(UserEmail email, UserUserName userName) {

        boolean isEmailRegistered = repository.existsByEmail(email);
        boolean isUserNameRegistered = repository.existsByUsername(userName);

        if (isEmailRegistered && isUserNameRegistered) {
            throw new DuplicateEmailException("Both the email " + email.value() + " and username " + userName.value() + " are already registered. Please use different credentials.");
        } else if (isEmailRegistered) {
            throw new DuplicateEmailException("The email " + email + " is already registered. Please use a different email.");
        } else if (isUserNameRegistered) {
            throw new DuplicateEmailException("The username " + userName + " is already registered. Please use a different username.");
        }
    }

}