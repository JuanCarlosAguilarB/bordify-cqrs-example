package com.bordify.shared.domain.bus.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent implements Serializable {

    private String eventId;
    private LocalDateTime occurredOn;

    public abstract String eventName();

    public DomainEvent() {
        this.eventId     = UUID.randomUUID().toString();
        this.occurredOn  = LocalDateTime.now();
    }

    public DomainEvent(String eventId, LocalDateTime occurredOn) {
        this.eventId     = eventId;
        this.occurredOn  = occurredOn;
    }

    public String eventId() {
        return eventId;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

}

