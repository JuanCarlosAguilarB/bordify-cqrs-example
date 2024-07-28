package com.bordify.auth.application.find;

import com.bordify.auth.domain.UserAuthInformation;
import com.bordify.auth.domain.UserAuthInformationRepository;
import com.bordify.auth.domain.UserUserName;
import com.bordify.user.domain.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthInformationFinder {

    private final UserAuthInformationRepository userRepository;

    /**
     * Retrieves a user by the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object corresponding to the specified username.
     * @throws UserNotFoundException If no user is found with the specified username.
     */
    public UserAuthInformation findUserByUsername(UserUserName username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

}
