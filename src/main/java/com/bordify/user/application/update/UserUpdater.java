package com.bordify.user.application.update;

import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

   public void update(UUID userId, Map<String, Object> userInfo){

       User userSearched = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Topic not found"));

       userInfo.forEach((key,value) -> {
           Field field = ReflectionUtils.findField(User.class, key);
           field.setAccessible(true);
           ReflectionUtils.setField(field, userSearched, value);
       });

    }
}
