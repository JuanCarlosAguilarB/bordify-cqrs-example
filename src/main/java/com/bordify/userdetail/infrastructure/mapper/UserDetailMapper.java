package com.bordify.userdetail.infrastructure.mapper;

import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.infrastructure.persistence.UserDetailEntity;
import org.springframework.stereotype.Service;

@Service
public class UserDetailMapper {

    public static UserDetail toDomain(UserDetailEntity userDetailEntity) {
        return UserDetail.builder()
                .id(userDetailEntity.getId())
                .username(userDetailEntity.getUsername())
                .firstName(userDetailEntity.getFirstName())
                .lastName(userDetailEntity.getLastName())
                .phoneNumber(userDetailEntity.getPhoneNumber())
                .build();
    }


    public static UserDetailEntity toEntity(UserDetail user) {
        return UserDetailEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
