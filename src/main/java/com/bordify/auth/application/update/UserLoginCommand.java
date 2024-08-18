package com.bordify.auth.application.update;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserLoginCommand implements Command {

    public String userName;
    public LocalDateTime loginDate;

}
