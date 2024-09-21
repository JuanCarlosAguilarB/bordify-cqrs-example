package com.bordify.userdetail.infrastructure.controllers;


import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.bus.query.QueryBus;
import com.bordify.shared.infrastructure.controllers.GetUserIdFromToken;
import com.bordify.userdetail.application.find.FindAllUserDetailsQuery;
import com.bordify.userdetail.application.find.FindUserDetailByIdQuery;
import com.bordify.userdetail.domain.AllUsersDetailResponse;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserDetail", description = "UserDetail management operations")
@AllArgsConstructor
@RestController
public class UserDetailGetController {

    private final GetUserIdFromToken getUserId;
    private final QueryBus bus;

    @Operation(summary = "Get information of the user", description = "Get a user", tags = {"UserDetail"})
    @GetMapping(value = "/v1/users/me/")
    public ResponseEntity<UserDetailResponse> getUser(HttpServletRequest request) {

        FindUserDetailByIdQuery query = new FindUserDetailByIdQuery(getUserId.getUserId());

        UserDetailResponse response = bus.ask(query);

        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);

    }

    @Operation(summary = "Get all users", description = "Get all users", tags = {"UserDetail"})
    @GetMapping(value = "/v1/users/")
    public ResponseEntity<PageResult<UserDetail>> retriveUsers(@RequestParam(defaultValue = "1") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {

        FindAllUserDetailsQuery query = new FindAllUserDetailsQuery(pageNumber,pageSize);
        AllUsersDetailResponse response = bus.ask(query);
//        PageResult<UserDetail> userList = userServices.getall(pagination);
        return ResponseEntity.ok(response);

    }


}
