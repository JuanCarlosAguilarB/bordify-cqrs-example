package com.bordify.auth.domain;

public interface AuthServices {

    public TokenUserResponse createToken(UserUserName userName);

    public UserWriteModel decode(TokenUserResponse token);

    public void ensureCredentialsAreValid(UserUserName userName, UserPassword password) throws CreadentialsNotValidException;

    public Boolean isValidToken(String token, String username);
}
