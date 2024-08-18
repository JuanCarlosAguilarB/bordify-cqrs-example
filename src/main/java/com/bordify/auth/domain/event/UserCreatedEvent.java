package com.bordify.auth.domain.event;

import com.bordify.shared.domain.bus.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedEvent extends DomainEvent {

    private UUID userId;
    private String email;
    private String userName;
    private LocalDateTime created;


    @Override
    public String eventName() {
        return "user.created";
    }
}
