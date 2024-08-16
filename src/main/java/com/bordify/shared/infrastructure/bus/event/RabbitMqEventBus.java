package com.bordify.shared.infrastructure.bus.event;


import com.bordify.shared.domain.bus.event.DomainEvent;
import com.bordify.shared.domain.bus.event.EventBus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqEventBus implements EventBus {


    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RabbitMqEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void publish(final List<DomainEvent> events) {
        for (DomainEvent event : events) {
            sendMessage(event);
        }
    }

    public void sendMessage(DomainEvent event)  {

        String jsonMessage = ObjectToJson(event);

        Message message = new Message(
                jsonMessage.getBytes(),
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .build()
        );
        rabbitTemplate.convertAndSend(exchangeName, event.eventName(), message);
    }

    private String ObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}