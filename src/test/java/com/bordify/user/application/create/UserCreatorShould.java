package com.bordify.user.application.create;

import com.bordify.user.domain.SecurityService;
import com.bordify.user.domain.UserModelTestService;
import com.bordify.user.domain.User;
import com.bordify.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserCreatorShould {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
    private final SecurityService securityServiceMock = Mockito.mock(SecurityService.class);

    private final UserCreator userCreator = new UserCreator(
            userRepositoryMock, securityServiceMock
    );


    @DisplayName("Create a valid user")
    @Test
    public void shouldCreateUser() {
        // the absence of errors is what tells me that the user was created correctly
        User userTest = UserModelTestService.createValidUserDomain();

        userCreator.createUser(userTest);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(userTest);

    }

}
