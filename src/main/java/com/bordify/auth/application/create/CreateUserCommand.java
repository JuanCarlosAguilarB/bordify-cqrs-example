package com.bordify.auth.application.create;

import com.bordify.shared.domain.bus.command.Command;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
public class CreateUserCommand implements Command {

    private UUID userId;
    private String email;
    private String userName;
    private String password;

}
