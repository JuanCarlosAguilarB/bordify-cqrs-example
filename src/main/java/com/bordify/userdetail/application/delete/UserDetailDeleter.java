package com.bordify.userdetail.application.delete;

import com.bordify.userdetail.domain.UserDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailDeleter {

    private final UserDetailRepository userDetailRepository;

    public void delete(UUID id) {
        userDetailRepository.delete(id);
    }

}
