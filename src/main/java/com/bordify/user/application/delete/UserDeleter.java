package com.bordify.user.application.delete;

import com.bordify.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDeleter {

    private final UserRepository userRepository;

    public void delete(UUID id) {
        userRepository.delete(id);
    }

}
