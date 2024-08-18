package com.bordify.auth.application.authenticate;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreadentialsValidateCommand implements Command {

    private String userName;
    private String password;

}
