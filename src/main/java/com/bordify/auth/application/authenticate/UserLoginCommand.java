package com.bordify.auth.application.authenticate;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class UserLoginCommand implements Command {

    public UUID userId;
    public LocalDateTime loginDate;

}
