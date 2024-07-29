package com.bordify.userdetail.application.update;

import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDetailUpdater {

    private final UserDetailRepository userDetailRepository;

    public void update(UUID userId, Map<String, Object> userInfo) {

        UserDetail userSearched = userDetailRepository.findById(userId).orElseThrow(() -> new RuntimeException("Topic not found"));

        userInfo.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(UserDetail.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userSearched, value);
        });

    }
}
