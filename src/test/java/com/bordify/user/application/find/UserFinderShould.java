package com.bordify.user.application.find;

import com.bordify.user.domain.User;
import com.bordify.user.domain.UserNotFoundException;
import com.bordify.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.bordify.user.domain.UserModelTestService.createValidUserDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserFinderShould {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

    private final UserFinder userFinder = new UserFinder(
            userRepositoryMock
    );


    @DisplayName("Find user by username")
    @Test
    public void shouldFindUserByUsername() {

        // Given
        String userName = "XXXX";
        User userTest = createValidUserDomain();
        userTest.setUsername(userName);

        // When
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.of(userTest));
        User user = userFinder.findUserByUsername(userName);

        // Then
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findByUsername(userName);
        assertEquals(userName, user.getUsername());
        assertEquals(userTest.getId(), user.getId());
    }

    @DisplayName("Throw exception when user not found")
    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserNotFound() {

        String userName = "XXXX";
        when(userRepositoryMock.findByUsername(userName)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userFinder.findUserByUsername(userName);
        });

    }



}
