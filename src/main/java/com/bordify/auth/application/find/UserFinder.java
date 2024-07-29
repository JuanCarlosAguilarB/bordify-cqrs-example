package com.bordify.auth.application.find;

import com.bordify.auth.domain.UserReadModel;
import com.bordify.auth.domain.UserRepository;
import com.bordify.auth.domain.UserUserName;
import com.bordify.userdetail.domain.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    /**
     * Retrieves a user by the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The UserDetail object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public UserReadModel findUserByUsername(UserUserName username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("UserDetail not found with username: " + username));
    }

}
