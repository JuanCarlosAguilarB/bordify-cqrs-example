package com.bordify.auth.application.authenticate;

import com.bordify.auth.application.find.UserAuthInformationFinder;
import com.bordify.auth.domain.Auth;
import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import com.bordify.auth.domain.UserAuthInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticator {

    private final UserAuthInformationFinder userFinder;
    private final AuthServices authServices;

    public AuthenticationToken authenticate(Auth auth) {

        authServices.ensureCredentialsAreValid(auth);

        String username = auth.getUserName();
        UserAuthInformation user = userFinder.findUserByUsername(username);

        return authServices.createToken(user);

    }

}