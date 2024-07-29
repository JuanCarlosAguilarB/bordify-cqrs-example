package com.bordify.auth.domain;

import com.bordify.shared.domain.CreadentialsNotValidException;

public interface AuthServices {

    public AuthenticationToken createToken(UserReadModel user);

    public UserReadModel decode(AuthenticationToken token);

    public void ensureCredentialsAreValid(Auth auth) throws CreadentialsNotValidException;

    public Boolean isValidToken(String token, String username);
}
