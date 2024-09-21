package com.bordify.userdetail.application.delete;

import com.bordify.shared.domain.bus.command.CommandHandler;
import com.bordify.userdetail.domain.UserDetailId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserDetailsCommandHandler implements CommandHandler<DeleteUserDetailsCommand> {

    private UserDetailDeleter service;

    @Override
    public void handle(DeleteUserDetailsCommand command) {
        service.delete(new UserDetailId(command.getId()));
    }
}
