package com.bordify.auth.domain;

import com.bordify.user.domain.User;

public interface AuthServices {

    public AuthenticationToken create(User user);
    public void authenticate(Auth auth);
}
