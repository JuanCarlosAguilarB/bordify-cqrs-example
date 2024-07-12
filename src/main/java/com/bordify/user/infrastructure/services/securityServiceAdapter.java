package com.bordify.user.infrastructure.services;

import com.bordify.user.domain.SecurityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class securityServiceAdapter implements SecurityService {

    private final PasswordEncoder passwordEncoder;

    public securityServiceAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String textToEncode) {
        return passwordEncoder.encode(textToEncode);
    }
}
