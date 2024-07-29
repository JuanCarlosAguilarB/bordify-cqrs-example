package com.bordify.auth.infrastructure.mapper;

import com.bordify.auth.domain.*;
import com.bordify.auth.infrastructure.persistence.UserAuthInformationEntity;
import com.bordify.shared.domain.UserUserId;

public class UserAuthInformationMapper {

    public static UserReadModel toDomain(UserAuthInformationEntity userEntity) throws UserEmailException {

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

    public static UserAuthInformationEntity toEntity(UserReadModel user) {

        return UserAuthInformationEntity.builder()
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
