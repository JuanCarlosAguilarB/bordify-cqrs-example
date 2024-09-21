package com.bordify.userdetail.application.find;

import com.bordify.shared.domain.bus.query.QueryHandler;
import com.bordify.userdetail.domain.UserDetailId;
import com.bordify.userdetail.domain.UserDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindUserDetailByIdQueryHandler implements QueryHandler<FindUserDetailByIdQuery, UserDetailResponse> {

    private final UserDetailFinder service;

    @Override
    public UserDetailResponse handle(FindUserDetailByIdQuery query) {

        return service.findUserById(
                new UserDetailId(query.getId())
        );

    }
}
