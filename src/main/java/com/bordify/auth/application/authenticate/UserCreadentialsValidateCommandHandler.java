package com.bordify.auth.application.authenticate;

import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.UserPassword;
import com.bordify.auth.domain.UserUserName;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserCreadentialsValidateCommandHandler implements CommandHandler<UserCreadentialsValidateCommand> {

    private final AuthServices authServices;

    @Override
    public void handle(UserCreadentialsValidateCommand command) {

        UserUserName userName = new UserUserName(command.getUserName());
        UserPassword password = new UserPassword(command.getPassword());

        authServices.ensureCredentialsAreValid(userName, password);

    }
}
