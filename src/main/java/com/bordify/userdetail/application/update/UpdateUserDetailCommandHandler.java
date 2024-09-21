package com.bordify.userdetail.application.update;

import com.bordify.shared.domain.bus.command.CommandHandler;
import com.bordify.userdetail.domain.UserDetailId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserDetailCommandHandler implements CommandHandler<UpdateUserDetailCommand> {

    private final UserDetailUpdater service;

    @Override
    public void handle(UpdateUserDetailCommand command) {
        service.update(new UserDetailId(command.getId()), command.getData());
    }
}
