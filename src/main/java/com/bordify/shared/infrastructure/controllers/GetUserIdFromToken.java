package com.bordify.shared.infrastructure.controllers;

import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class GetUserIdFromToken {

    private final AuthServices authServices;
    private final GetTokenFromRequest getTokenFromRequest;

    public UUID getUserId() {

        String token = getTokenFromRequest.getToken();

        if (token == null) {
            return null;
        }

        token = String.valueOf(authServices.decode(
            AuthenticationToken.builder().token(token).build()
        ).getUserId());

        return UUID.fromString(token);
    }

}
