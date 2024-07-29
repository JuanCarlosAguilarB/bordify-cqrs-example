package com.bordify.userdetails.infrastructure.controllers;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.infrastructure.controllers.GetUserIdFromToken;
import com.bordify.userdetails.application.find.UserFinder;
import com.bordify.userdetails.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "User", description = "User management operations")
@AllArgsConstructor
@RestController
public class UserGetController {

    private final UserFinder userServices;
    private final GetUserIdFromToken getUserId;


    @Operation(summary = "Get information of the user", description = "Get a user", tags = {"User"})
    @GetMapping(value = "/v1/users/me/")
    public ResponseEntity<UserResponse> getUser(HttpServletRequest request) {

        UUID userId = getUserId.getUserId();
        User user = userServices.findUserById(userId);

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(userResponse);

    }

    @Operation(summary = "Get all users", description = "Get all users", tags = {"User"})
    @GetMapping(value = "/v1/users/")
    public ResponseEntity<PageResult<User>> retriveUsers(@RequestParam(defaultValue = "1") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {

        PaginationRequest pagination = new PaginationRequest(pageNumber, pageSize);
        PageResult<User> userList = userServices.getall(pagination);

        return ResponseEntity.ok(userList);

    }


}
