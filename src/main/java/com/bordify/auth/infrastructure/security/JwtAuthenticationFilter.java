package com.bordify.auth.infrastructure.security;

import com.bordify.auth.domain.AuthServices;
import com.bordify.auth.domain.AuthenticationToken;
import com.bordify.auth.domain.UserReadModel;
import com.bordify.shared.infrastructure.controllers.GetTokenFromRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Filter class responsible for JWT authentication.
 * Validates JWT tokens from incoming requests and sets the authentication context if valid.
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;
    private final AuthServices authServices;
    private final GetTokenFromRequest getTokenFromRequest;

    /**
     * Performs the JWT token validation and sets the authentication context if valid.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain for the request.
     * @throws ServletException If an error occurs during the filter execution.
     * @throws IOException      If an I/O error occurs during the filter execution.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest.getToken(request);

        if (token == null) {
//            ApiResponseHelper.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Bad Request", "El token no puede ser nulo");
            filterChain.doFilter(request, response);
            return;

        }

        UserReadModel userReadModel = authServices.decode(AuthenticationToken.builder().token(token).build());

        final String username = userReadModel.userName().value();
        final UUID userId = userReadModel.id().value();

        if (username == null || userId == null) {
            ApiResponseHelper.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Bad Request", "El token is not valid");
            return;
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!authServices.isValidToken(token, userDetails.getUsername())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

        }

        filterChain.doFilter(request, response);
    }

}