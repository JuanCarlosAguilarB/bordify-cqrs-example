package com.bordify.auth.domain;

import com.bordify.shared.domain.UserUserName;

public interface AuthServices {

    public TokenUserResponse createToken(UserUserName userName);

    public UserWriteModel decode(TokenUserResponse token);

    public void ensureCredentialsAreValid(UserUserName userName, UserPassword password) throws CreadentialsNotValidException;

    public Boolean isValidToken(String token, String username);
}
