package com.bordify.shared.domain;

public interface CommandHandler<T extends Command> {

    public Class<T> getCommandType();

    public void handle(T command);

}