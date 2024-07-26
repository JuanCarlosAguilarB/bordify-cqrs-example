package com.bordify.shared.domain;

public interface CommandHandler<T extends Command> {

    public void handle(T command);

}