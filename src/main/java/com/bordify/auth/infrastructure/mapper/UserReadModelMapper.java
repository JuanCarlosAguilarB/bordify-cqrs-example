package com.bordify.auth.infrastructure.mapper;

import com.bordify.auth.domain.*;
import com.bordify.auth.infrastructure.persistence.UserReadModelEntity;
import com.bordify.shared.domain.UserUserId;

public class UserReadModelMapper {

    public static UserReadModel toDomain(UserReadModelEntity userReadModelEntity) throws UserEmailException {

        return new UserReadModel(
                new UserUserId(userReadModelEntity.getUserId()),
                new UserEmail(userReadModelEntity.getEmail()),
                new UserUserName(userReadModelEntity.getUserName()),
//                new UserPassword(userReadModelEntity.getPassword()),
                new UserIsVerified(userReadModelEntity.getIsVerified()),
                new UserDateCreated(userReadModelEntity.getCreated()),
                new UserDateLastLogin(userReadModelEntity.getLastLogin())
        );
    }

    public static UserReadModelEntity toEntity(UserReadModel user) {

        return UserReadModelEntity.builder()
                .userId(user.id().value())
                .userName(user.userName().value())
                .email(user.email().value())
//                .password(user.password().value())
                .isVerified(user.isVerified().value())
                .lastLogin(user.lastLogin().value())
                .created(user.created().value())
                .build();
    }
}
