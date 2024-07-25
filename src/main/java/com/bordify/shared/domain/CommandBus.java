package com.bordify.shared.domain;

public interface CommandBus {

    public void send(Command command);

}
