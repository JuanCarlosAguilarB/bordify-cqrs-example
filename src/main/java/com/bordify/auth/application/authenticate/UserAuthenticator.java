package com.bordify.auth.application.authenticate;

import com.bordify.auth.application.find.UserFinder;
import com.bordify.auth.domain.*;
import com.bordify.shared.domain.bus.command.CommandBus;
import com.bordify.shared.domain.bus.query.QueryBus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserAuthenticator {

    private final UserFinder userFinder;
    private final AuthServices authServices;
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public TokenUserResponse authenticate(Auth auth) {

        authServices.ensureCredentialsAreValid(auth);

        String username = auth.getUserName();
        UserWriteModel user = userFinder.findUserByUsername(new UserUserName(username));

        TokenUserResponse token = authServices.createToken(user);

        LocalDateTime loginDate = LocalDateTime.now();
        commandBus.send(new UserLoginCommand(user.id().value(), loginDate));

        return token;

    }

}
