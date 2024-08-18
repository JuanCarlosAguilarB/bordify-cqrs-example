package com.bordify.auth.application.authenticate;

import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginCommandHandler implements CommandHandler<UserLoginCommand> {


    @Override
    public void handle(UserLoginCommand command) {

    }
}
