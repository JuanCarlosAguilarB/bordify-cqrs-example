package com.bordify.shared.infrastructure.bus;

import com.bordify.shared.domain.bus.command.Command;
import com.bordify.shared.domain.bus.command.CommandBus;
import com.bordify.shared.domain.bus.command.CommandHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
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
       applicationContext.getBeansOfType(CommandHandler.class).forEach(this::accept);
    }

    @Override
    public void send(Command command) {
        CommandHandler handler = commandHandlerMap.get(command.getClass());

        if (handler == null) throw new RuntimeException("No handler found for command type: " + command.getClass().getName());

        handler.handle(command);
    }

    private void accept(String key, CommandHandler value) {
        Class<? extends Command> commandType = findCommandType(value);
        commandHandlerMap.put(commandType, value);
    }

    private Class<? extends Command> findCommandType(CommandHandler handler) {
        ParameterizedType parameterizedType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
        return (Class<? extends Command>) parameterizedType.getActualTypeArguments()[0];
    }
}
