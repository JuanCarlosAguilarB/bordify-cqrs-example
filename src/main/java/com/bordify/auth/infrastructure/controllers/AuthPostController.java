package com.bordify.auth.infrastructure.controllers;

import com.bordify.auth.application.authenticate.ObtainTokenUserQuery;
import com.bordify.auth.application.authenticate.UserCreadentialsValidateCommand;
import com.bordify.auth.application.update.UserLoginCommand;
import com.bordify.auth.domain.TokenUserResponse;
import com.bordify.shared.domain.bus.command.CommandBus;
import com.bordify.shared.domain.bus.query.QueryBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Auth", description = "Authentication operations")
@AllArgsConstructor
public class AuthPostController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A ResponseEntity with the JWT token and user information.
     */
    @Operation(summary = "Authenticate a user", description = "Authenticates a user and returns a JWT token", tags = {"Auth"})
    @PostMapping("/v1/login/")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        // Validate the credentials
        UserCreadentialsValidateCommand command = new UserCreadentialsValidateCommand(username, password);
        commandBus.send(command);

        // Obtain the token
        ObtainTokenUserQuery query = new ObtainTokenUserQuery(username);
        TokenUserResponse response =  queryBus.ask(query);

        // Update the user login date
        // this command could have all the information needed, for example, ip address, user agent, etc.
        LocalDateTime loginDate = LocalDateTime.now();
        commandBus.send(new UserLoginCommand(username, loginDate));

        return ResponseEntity.ok(response);

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

class LoginRequest {
    private final String userName;
    private final String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;    }

    public String getPassword() {
        return password;
    }
}