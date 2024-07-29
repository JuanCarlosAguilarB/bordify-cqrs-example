package com.bordify.userdetails.infrastructure.mapper;

import com.bordify.userdetails.domain.User;
import com.bordify.userdetails.infrastructure.persistence.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }


    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
