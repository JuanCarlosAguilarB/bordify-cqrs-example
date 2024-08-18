package com.bordify.shared.domain.bus.query;

public interface QueryBus {

    public <T extends Response> T ask(Query query);

}
