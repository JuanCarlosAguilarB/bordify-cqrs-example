package com.bordify.shared.domain.bus.command;

public interface CommandBus {

    public void send(Command command);

}
