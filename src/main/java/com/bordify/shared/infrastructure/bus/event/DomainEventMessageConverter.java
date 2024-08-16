package com.bordify.shared.infrastructure.bus.event;

import com.bordify.shared.domain.bus.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;

public class DomainEventMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper;
    private final Class<? extends DomainEvent> eventClass;

    public DomainEventMessageConverter(ObjectMapper objectMapper, Class<? extends DomainEvent> eventClass) {
        this.objectMapper = objectMapper;
        this.eventClass = eventClass;
    }

    @Override
    public Object fromMessage(Message message)  {
        String json = new String(message.getBody());
        try {
            return objectMapper.readValue(json, eventClass);
        } catch (JsonProcessingException e) {
       System.err.println("Error parsing JSON message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) {
        byte[] body = null;
        try {
            body = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Message(body, messageProperties);
    }
}