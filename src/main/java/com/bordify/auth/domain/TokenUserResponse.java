package com.bordify.auth.domain;

import com.bordify.shared.domain.bus.query.Response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenUserResponse implements Response {

    private String token;
    private String refreshToken;

}
