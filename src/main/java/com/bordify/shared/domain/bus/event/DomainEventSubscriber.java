package com.bordify.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DomainEventSubscriber {
    Class<? extends DomainEvent>[] value();
}
//
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//@Inherited
//public @interface DomainEventSubscriber {
//    Class<? extends DomainEvent>[] value();
//}