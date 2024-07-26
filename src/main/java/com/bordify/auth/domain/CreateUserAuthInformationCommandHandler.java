package com.bordify.auth.domain;

import com.bordify.auth.application.create.UserAuthInformationCreator;
import com.bordify.shared.domain.CommandHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;


@Builder
@Data
@Service("CreateUserAuthInformationCommandHandler")
@AllArgsConstructor
public class CreateUserAuthInformationCommandHandler implements CommandHandler<CreateUserAuthInformationCommand> {

    private final UserAuthInformationCreator service;

    @Override
    public void handle(CreateUserAuthInformationCommand command) {
        System.out.println("CreateUserAuthInformationCommandHandler");

        UserAuthInformation user = UserAuthInformation.builder()
                .userId(command.getUserId())
                .email(command.getEmail())
                .username(command.getUsername())
                .password(command.getPassword())
                .build();

        service.createUser(user);
    }

}

/*
TODO:
    - modify services of createUser because, some params should'n be required (as isVerified).
    - i need to read all commands and handlers and realize the mapping between them.
 */


