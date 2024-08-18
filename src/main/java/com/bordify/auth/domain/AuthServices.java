package com.bordify.auth.domain;

import com.bordify.shared.domain.CreadentialsNotValidException;

public interface AuthServices {

    public TokenUserResponse createToken(UserWriteModel user);

    public UserWriteModel decode(TokenUserResponse token);

    public void ensureCredentialsAreValid(Auth auth) throws CreadentialsNotValidException;

    public Boolean isValidToken(String token, String username);
}
