package com.bordify.auth.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bordify.auth.application.find.UserAuthInformationFinder;
import com.bordify.auth.domain.Auth;
import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import com.bordify.auth.domain.UserAuthInformation;
import com.bordify.shared.domain.CreadentialsNotValidException;
import com.bordify.user.domain.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AuthServicesAdapter implements AuthServices {


    //    @Value("${jwt.secret}")
    private final String secret = "secret";
    private final LocalDate now = LocalDate.now();
    private final Algorithm algorithm = Algorithm.HMAC256(this.secret);
    private final UserAuthInformationFinder userFinder;
    private final SecurityService securityService;

    @Override
    public AuthenticationToken createToken(UserAuthInformation user) {


        //    @Value("${jwt.refreshTokenExpirationInDays:11}")
        int refreshTokenExpirationInDays = 1100;

        //    @Value("${jwt.accessTokenExpirationInDays:10}")
        int accessTokenExpirationInDays = 1000;

        String username = user.getUsername();

        String accessToken = generateToken(accessTokenExpirationInDays, username);
        String refreshToken = generateToken(refreshTokenExpirationInDays, username);

        return AuthenticationToken.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public void authenticate(Auth auth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getUserName(),
                        auth.getPassword()
                )
        );
    }

    @Override
    public void ensureCredentialsAreValid(Auth auth) {

        UserAuthInformation userAuthInformation = userFinder.findUserByUsername(auth.getUserName());

        if (!securityService.matches(auth.getPassword(), userAuthInformation.getPassword())) {
            throw new CreadentialsNotValidException("Invalid credentials");
        }

        /* this method replaces the following code,

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getUserName(),
                        auth.getPassword()
                )
        );
        */

    }

    /**
     * Generates a JWT access token with the specified expiration and username.
     *
     * @param expirationInDays The expiration duration of the token in days.
     * @param subject The username associated with the token.
     * @return The generated JWT access token.
     */
    private String generateToken(int expirationInDays, String subject) {

        LocalDate expiryDate = now.plusDays(expirationInDays);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(java.sql.Timestamp.valueOf(now.atStartOfDay()))
                .withExpiresAt(java.sql.Timestamp.valueOf(expiryDate.atStartOfDay()))
                .sign(algorithm);
    }

}
