package com.bordify.auth.infrastructure.mapper;

import com.bordify.auth.domain.*;
import com.bordify.auth.infrastructure.persistence.UserWriteModelEntity;
import com.bordify.shared.domain.UserUserId;

public class UserWriteModelMapper {

    public static UserWriteModel toDomain(UserWriteModelEntity userWriteModelEntity) throws UserEmailException {

        return new UserWriteModel(
                new UserUserId(userWriteModelEntity.getUserId()),
                new UserEmail(userWriteModelEntity.getEmail()),
                new UserUserName(userWriteModelEntity.getUserName()),
                new UserPassword(userWriteModelEntity.getPassword()),
                new UserIsVerified(userWriteModelEntity.getIsVerified()),
                new UserDateCreated(userWriteModelEntity.getCreated()),
                new UserDateLastLogin(userWriteModelEntity.getLastLogin())
        );
    }

    public static UserWriteModelEntity toEntity(UserWriteModel user) {

        return UserWriteModelEntity.builder()
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
