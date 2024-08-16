package com.bordify.userdetail.application.create;

import com.bordify.auth.domain.SecurityService;
import com.bordify.userdetail.domain.UserDetailRepository;
import com.bordify.userdetail.domain.UserModelTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserDetailCreatorShould {

    private final UserDetailRepository userDetailRepositoryMock = Mockito.mock(UserDetailRepository.class);
    private final SecurityService securityServiceMock = Mockito.mock(SecurityService.class);

    private final UserDetailCreator userDetailCreator = new UserDetailCreator(
            userDetailRepositoryMock, securityServiceMock
    );


    @DisplayName("Create a valid user")
    @Test
    public void shouldCreateUser() {
        // the absence of errors is what tells me that the user was created correctly
        User userTest = UserModelTestService.createValidUserDomain();

        userDetailCreator.createUser(userTest);
        Mockito.verify(userDetailRepositoryMock, Mockito.times(1)).save(userTest);

    }

}
