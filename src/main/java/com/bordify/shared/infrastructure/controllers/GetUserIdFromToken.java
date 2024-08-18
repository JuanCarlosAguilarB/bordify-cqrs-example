package com.bordify.shared.infrastructure.controllers;

import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.TokenUserResponse;
import com.bordify.auth.domain.UserWriteModel;
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

        UserWriteModel user = authServices.decode(
                TokenUserResponse.builder().token(token).build()
        );

        return user.id().value();
    }

}
