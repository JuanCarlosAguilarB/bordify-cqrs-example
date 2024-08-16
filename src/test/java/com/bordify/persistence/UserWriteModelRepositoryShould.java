package com.bordify.persistence;

import com.bordify.userdetail.infrastructure.persistence.UserDetailEntity;
import com.bordify.userdetail.infrastructure.persistence.UserDetailJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.bordify.userdetail.domain.UserModelTestService.createValidUserEntity;

@DataJpaTest
public class UserWriteModelRepositoryShould {

    @Autowired
    private UserDetailJpaRepository userRepository;

    @DisplayName("Should find user by email")
    @Test
    public void shouldFindUserByEmail() {
        UserDetailEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        boolean hasUser = userRepository.existsByEmail(userTest.getEmail());

        Assertions.assertTrue(hasUser);
    }

    @DisplayName("Should find user by userName")
    @Test
    public void shouldFindUserByUsername() {
        UserDetailEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        boolean hasUser = userRepository.existsByUsername(userTest.getUsername());

        Assertions.assertTrue(hasUser);
    }

    @DisplayName("Should get an user by userName")
    @Test
    public void shouldGetAnUserByUsername() {
        UserDetailEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        Optional<UserDetailEntity> user = userRepository.findByUsername(userTest.getUsername());

        Assertions.assertEquals(Optional.of(userTest), user);
    }


    @DisplayName("Should get an user by email")
    @Test
    public void shouldGetAnUserByEmail() {
        UserDetailEntity userTest = createValidUserEntity();
        userRepository.save(userTest);

        Optional<UserDetailEntity> user = userRepository.findByEmail(userTest.getEmail());

        Assertions.assertEquals(userTest, user.isPresent() ? user.get() : null);
    }


//    public User createValidEntity() {
//        return User.builder()
//                .id(UUID.randomUUID())
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@example.com")
//                .build();
//    }
}