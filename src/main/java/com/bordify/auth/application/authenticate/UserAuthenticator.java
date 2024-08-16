package com.bordify.auth.application.authenticate;

import com.bordify.auth.application.find.UserFinder;
import com.bordify.auth.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticator {

    private final UserFinder userFinder;
    private final AuthServices authServices;

    public AuthenticationToken authenticate(Auth auth) {

        authServices.ensureCredentialsAreValid(auth);

        String username = auth.getUserName();
        UserWriteModel user = userFinder.findUserByUsername(new UserUserName(username));

        return authServices.createToken(user);

    }

}
