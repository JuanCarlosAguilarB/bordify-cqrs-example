package com.bordify.shared.domain.bus.command.event;

import java.util.List;

public interface EventBus {

    void publish(final List<DomainEvent> events);

}
