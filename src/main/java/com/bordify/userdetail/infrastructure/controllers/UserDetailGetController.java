package com.bordify.userdetail.infrastructure.controllers;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.shared.infrastructure.controllers.GetUserIdFromToken;
import com.bordify.userdetail.application.find.UserDetailFinder;
import com.bordify.userdetail.domain.UserDetail;
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

@Tag(name = "UserDetail", description = "UserDetail management operations")
@AllArgsConstructor
@RestController
public class UserDetailGetController {

    private final UserDetailFinder userServices;
    private final GetUserIdFromToken getUserId;


    @Operation(summary = "Get information of the user", description = "Get a user", tags = {"UserDetail"})
    @GetMapping(value = "/v1/users/me/")
    public ResponseEntity<UserDetailResponse> getUser(HttpServletRequest request) {

        UUID userId = getUserId.getUserId();
        UserDetail user = userServices.findUserById(userId);

        UserDetailResponse userDetailResponse = UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(userDetailResponse);

    }

    @Operation(summary = "Get all users", description = "Get all users", tags = {"UserDetail"})
    @GetMapping(value = "/v1/users/")
    public ResponseEntity<PageResult<UserDetail>> retriveUsers(@RequestParam(defaultValue = "1") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {

        PaginationRequest pagination = new PaginationRequest(pageNumber, pageSize);
        PageResult<UserDetail> userList = userServices.getall(pagination);

        return ResponseEntity.ok(userList);

    }


}
