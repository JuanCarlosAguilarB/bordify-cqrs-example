package com.bordify.auth.application.authenticate;

import com.bordify.auth.application.update.LastLoginDateUpdater;
import com.bordify.auth.domain.UserDateLastLogin;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginCommandHandler implements CommandHandler<UserLoginCommand> {

    private final LastLoginDateUpdater lastLoginDateUpdater;

    @Override
    public void handle(UserLoginCommand command) {

        UserUserId userId = new UserUserId(command.getUserId());
        UserDateLastLogin lastLoginDate = new UserDateLastLogin(command.getLoginDate());

        lastLoginDateUpdater.update(userId, lastLoginDate);

    }
}
