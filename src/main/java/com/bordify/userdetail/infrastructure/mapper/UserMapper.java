package com.bordify.userdetail.infrastructure.mapper;

import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.infrastructure.persistence.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static UserDetail toDomain(UserEntity userEntity) {
        return UserDetail.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }


    public static UserEntity toEntity(UserDetail user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
