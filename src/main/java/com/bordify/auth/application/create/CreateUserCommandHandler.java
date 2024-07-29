package com.bordify.auth.application.create;

import com.bordify.auth.domain.UserEmail;
import com.bordify.auth.domain.UserPassword;
import com.bordify.auth.domain.UserUserName;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserCreator service;

    @Override
    public void handle(CreateUserCommand command) {
        System.out.println("CreateUserCommandHandler");

        service.createUser(
                new UserUserId(command.getUserId()),
                new UserEmail(command.getEmail()),
                new UserUserName(command.getUsername()),
                new UserPassword(command.getPassword())
        );

    }
}
