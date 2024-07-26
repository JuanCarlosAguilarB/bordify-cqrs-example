package com.bordify.auth.infrastructure.mapper;

import com.bordify.auth.domain.UserAuthInformation;
import com.bordify.auth.infrastructure.persistence.UserAuthInformationEntity;

public class UserAuthInformationMapper {

    public static UserAuthInformation toDomain(UserAuthInformationEntity userEntity) {
        return UserAuthInformation.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .isVerified(userEntity.getIsVerified())
                .created(userEntity.getCreated())
                .lastLogin(userEntity.getLastLogin())
                .build();
    }

    public static UserAuthInformationEntity toEntity(UserAuthInformation user) {

        return UserAuthInformationEntity.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .isVerified(user.getIsVerified())
                .created(user.getCreated())
                .build();
    }
}
