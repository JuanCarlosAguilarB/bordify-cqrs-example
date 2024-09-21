package com.bordify.userdetail.application.update;

import com.bordify.shared.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateUserDetailCommand implements Command {
    UUID id;
    Map<String, Object> data;
}
