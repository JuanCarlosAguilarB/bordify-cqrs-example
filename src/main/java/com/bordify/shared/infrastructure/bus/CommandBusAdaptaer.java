package com.bordify.shared.infrastructure.bus;

import com.bordify.shared.domain.Command;
import com.bordify.shared.domain.CommandBus;
import com.bordify.shared.domain.CommandHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class CommandBusAdaptaer implements CommandBus {

    private final ApplicationContext applicationContext;
    private Map< Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
    public CommandBusAdaptaer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

   @PostConstruct
    private void registerCommandHandlers() {
       applicationContext.getBeansOfType(CommandHandler.class).forEach((key, value) -> {
           commandHandlerMap.put(value.getCommandType(), value);
       });
    }

    @Override
    public void send(Command command) {
        CommandHandler commandHandler = commandHandlerMap.get(command.getClass());
        commandHandler.handle(command);
    }
}
