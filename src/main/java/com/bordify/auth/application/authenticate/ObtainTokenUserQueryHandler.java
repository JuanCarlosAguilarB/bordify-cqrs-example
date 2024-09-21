package com.bordify.auth.application.authenticate;

import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.TokenUserResponse;
import com.bordify.shared.domain.UserUserName;
import com.bordify.shared.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObtainTokenUserQueryHandler implements QueryHandler<ObtainTokenUserQuery, TokenUserResponse> {

    private final AuthServices authServices;

    @Override
    public TokenUserResponse handle(ObtainTokenUserQuery query) {
        UserUserName userName = new UserUserName(query.userName);
        return authServices.createToken(userName);
    }
}
