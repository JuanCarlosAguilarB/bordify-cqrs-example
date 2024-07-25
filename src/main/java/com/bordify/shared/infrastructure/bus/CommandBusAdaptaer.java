package com.bordify.shared.infrastructure.bus;

import com.bordify.auth.domain.CreateUserAuthInformationCommand;
import com.bordify.shared.domain.Command;
import com.bordify.shared.domain.CommandBus;
import com.bordify.shared.domain.CommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommandBusAdaptaer implements CommandBus {

    private final ApplicationContext context;
    Map<Class<? extends Command>, String> handlers = new HashMap<>();

    public CommandBusAdaptaer(ApplicationContext context) {
        this.context = context;
    }


    @Override
    public void send(Command command) {
        handlers.put(CreateUserAuthInformationCommand.class, "CreateUserAuthInformationCommandHandler");

        CommandHandler handler = (CommandHandler) context.getBean(handlers.get(command.getClass()));

        if (handler != null) {
            try {
                handler.handle(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
