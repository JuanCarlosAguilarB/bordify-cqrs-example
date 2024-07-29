package com.bordify.auth.infrastructure.mapper;

import com.bordify.auth.domain.*;
import com.bordify.auth.infrastructure.persistence.UserEntity;
import com.bordify.shared.domain.UserUserId;

public class UserMapper {

    public static UserReadModel toDomain(UserEntity userEntity) throws UserEmailException {

        return new UserReadModel(
                new UserUserId(userEntity.getUserId()),
                new UserEmail(userEntity.getEmail()),
                new UserUserName(userEntity.getUserName()),
                new UserPassword(userEntity.getPassword()),
                new UserIsVerified(userEntity.getIsVerified()),
                new UserDateCreated(userEntity.getCreated()),
                new UserDateLastLogin(userEntity.getLastLogin())
        );
    }

    public static UserEntity toEntity(UserReadModel user) {

        return UserEntity.builder()
                .userId(user.id().value())
                .userName(user.userName().value())
                .email(user.email().value())
                .password(user.password().value())
                .isVerified(user.isVerified().value())
                .lastLogin(user.lastLogin().value())
                .created(user.created().value())
                .build();
    }
}
