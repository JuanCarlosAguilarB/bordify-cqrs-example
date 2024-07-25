package com.bordify.auth.infrastructure.controllers;

import com.bordify.auth.application.authenticate.UserAuthenticator;
import com.bordify.auth.domain.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth", description = "Authentication operations")
@AllArgsConstructor
public class AuthPostController {

    private final UserAuthenticator userAuthenticator;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A ResponseEntity with the JWT token and user information.
     */
    @Operation(summary = "Authenticate a user", description = "Authenticates a user and returns a JWT token", tags = {"Auth"})
    @PostMapping("/v1/login/")
    public ResponseEntity<?> authenticateUser(@RequestBody Auth loginRequest) {

        return ResponseEntity.ok(userAuthenticator.authenticate(loginRequest));

    }


//    // authentication is responsible for verifying that the credentials
//    // provided by the user (username and password) are valid.
//    // If the credentials are valid, the user is authenticated and a JWT token is returned.
//    // else, an error message is returned by the authentication manager.
//    public void authenticate(Auth auth) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        auth.getUserName(),
//                        auth.getPassword()
//                )
//        );
//    }

}
