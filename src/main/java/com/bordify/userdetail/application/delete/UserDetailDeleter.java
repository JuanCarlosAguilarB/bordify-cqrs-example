package com.bordify.userdetail.application.delete;

import com.bordify.userdetail.domain.UserDetailId;
import com.bordify.userdetail.domain.UserDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailDeleter {

    private final UserDetailRepository userDetailRepository;

    public void delete(UserDetailId id) {
        userDetailRepository.delete(id);
    }

}
