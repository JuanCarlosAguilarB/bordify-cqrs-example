package com.bordify.userdetail.application.find;

import com.bordify.userdetail.domain.UserDetailRepository;
import com.bordify.userdetail.domain.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.bordify.userdetail.domain.UserModelTestService.createValidUserDomain;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserDetailFinderShould {

    private final UserDetailRepository userDetailRepositoryMock = Mockito.mock(UserDetailRepository.class);

    private final UserDetailFinder userDetailFinder = new UserDetailFinder(
            userDetailRepositoryMock
    );


    @DisplayName("Find user by username")
    @Test
    public void shouldFindUserByUsername() {

        // Given
        String userName = "XXXX";
        User userTest = createValidUserDomain();
        userTest.setUsername(userName);

        // When
        when(userDetailRepositoryMock.findByUsername(userName)).thenReturn(Optional.of(userTest));
        User user = userDetailFinder.findUserByUsername(userName);

        // Then
        Mockito.verify(userDetailRepositoryMock, Mockito.times(1)).findByUsername(userName);
        assertEquals(userName, user.getUsername());
        assertEquals(userTest.getId(), user.getId());
    }

    @DisplayName("Throw exception when user not found")
    @Test
    public void shouldThrowUserNotFoundExceptionWhenUserNotFound() {

        String userName = "XXXX";
        when(userDetailRepositoryMock.findByUsername(userName)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userDetailFinder.findUserByUsername(userName);
        });

    }


}
