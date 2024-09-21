package com.bordify.userdetail.application.create;


import com.bordify.shared.domain.UserUserName;
import com.bordify.shared.domain.bus.command.CommandHandler;
import com.bordify.userdetail.domain.UserDetailFirstName;
import com.bordify.userdetail.domain.UserDetailId;
import com.bordify.userdetail.domain.UserDetailLastName;
import com.bordify.userdetail.domain.UserDetailPhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CreateUserDetailCommandHandler implements CommandHandler<CreateUserDetailCommand> {

    private final UserDetailCreator service;

    @Override
    public void handle(CreateUserDetailCommand command) {
        System.out.println("CreateUserDetailCommand");

        service.createUser(
                new UserDetailId(command.getId()),
                new UserDetailFirstName(command.getFirstName()),
                new UserDetailLastName(command.getLastName()),
                new UserDetailPhoneNumber(command.getPhoneNumber()),
                new UserUserName(command.getUserName())
        );

    }


}
