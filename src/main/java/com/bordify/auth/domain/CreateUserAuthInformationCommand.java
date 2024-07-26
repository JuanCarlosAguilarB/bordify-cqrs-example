package com.bordify.auth.domain;

import com.bordify.shared.domain.bus.command.Command;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
public class CreateUserAuthInformationCommand implements Command {

    private UUID userId;
    private String email;
    private String username;
    private String password;

}
