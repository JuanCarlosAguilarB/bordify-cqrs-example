package com.bordify.auth.domain;

import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.UserUserName;

import java.time.LocalDateTime;

import static com.bordify.shared.domain.FactoryValues.generateRandomAlphanumeric;
import static com.bordify.shared.domain.FactoryValues.generateRandomEmail;
import static com.bordify.shared.domain.GeneratorValuesRandom.generateRandomValue;

public class UserObjectMother {


    public static UserWriteModel getRandomUser(){

        UserUserId userId = new UserUserId(generateRandomAlphanumeric(generateRandomValue(5,20)));
        UserEmail email = new UserEmail(generateRandomEmail());
        UserUserName userName = new UserUserName(generateRandomAlphanumeric(generateRandomValue(5,20)));
        UserPassword password = new UserPassword(generateRandomAlphanumeric(generateRandomValue(5,20)));
        UserIsVerified isVerified = new UserIsVerified(false);
        UserDateCreated created = new UserDateCreated(LocalDateTime.now());
        UserDateLastLogin lastLogin = new UserDateLastLogin(null);

        return new UserWriteModel(
                userId, email, userName, password, isVerified, created, lastLogin
        );

    }

}
