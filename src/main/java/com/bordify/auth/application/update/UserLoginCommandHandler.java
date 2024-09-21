package com.bordify.auth.application.update;

import com.bordify.auth.domain.UserDateLastLogin;
import com.bordify.shared.domain.UserUserName;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginCommandHandler implements CommandHandler<UserLoginCommand> {

    private final LastLoginDateUpdater lastLoginDateUpdater;

    @Override
    public void handle(UserLoginCommand command) {

        UserUserName userName = new UserUserName(command.getUserName());
        UserDateLastLogin lastLoginDate = new UserDateLastLogin(command.getLoginDate());

        lastLoginDateUpdater.update(userName, lastLoginDate);

    }
}
