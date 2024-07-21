package com.bordify.auth.domain;

import com.bordify.user.domain.User;

public interface AuthServices {

    public AuthenticationToken createToken(User user);
    public void authenticate(Auth auth);
}
