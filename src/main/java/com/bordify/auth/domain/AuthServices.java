package com.bordify.auth.domain;


public interface AuthServices {

    public AuthenticationToken createToken(UserAuthInformation user);
    public void authenticate(Auth auth);
}
