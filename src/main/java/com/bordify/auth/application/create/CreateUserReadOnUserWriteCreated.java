package com.bordify.auth.application.create;

import com.bordify.auth.domain.*;
import com.bordify.auth.domain.event.UserCreatedEvent;
import com.bordify.shared.domain.UserUserId;
import com.bordify.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({UserCreatedEvent.class})
public class CreateUserReadOnUserWriteCreated {

    private final UserReadModelRepository repository;

    public CreateUserReadOnUserWriteCreated(UserReadModelRepository repository) {this.repository = repository;}

    public void on(UserCreatedEvent  event) {
            UserUserId userId = new UserUserId(event.getUserId());
            UserEmail email = new UserEmail(event.getEmail());
            UserUserName userName = new UserUserName(event.getUserName());
            UserIsVerified isVerified = new UserIsVerified(false);
            UserDateCreated created = new UserDateCreated(event.getCreated());
            UserDateLastLogin lastLogin = new UserDateLastLogin(null);
            UserReadModel user = new UserReadModel(userId, email, userName, isVerified, created, lastLogin);
            repository.save(user);
            System.out.println("User %s created: " + event.getUserId());

    }

}
