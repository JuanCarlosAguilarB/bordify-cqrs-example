package com.bordify.auth.domain;


import com.bordify.shared.domain.CreadentialsNotValidException;

public interface AuthServices {

    public AuthenticationToken createToken(UserAuthInformation user);
    public void authenticate(Auth auth);
    public void ensureCredentialsAreValid(Auth auth) throws CreadentialsNotValidException;

}
