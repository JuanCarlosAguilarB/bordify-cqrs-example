package com.bordify.shared.infrastructure.bus.event;

import com.bordify.shared.domain.bus.event.DomainEvent;
import com.bordify.shared.domain.bus.event.DomainEventSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import org.reflections.Reflections;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Set;

import static com.bordify.shared.infrastructure.bus.event.RabbitMqQueueNameFormatter.*;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    private final String retryExchangeName = RabbitMqExchangeNameFormatter.retry(exchangeName);
    private final String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

    private final AmqpAdmin amqpAdmin;
    private final ObjectMapper objectMapper;
    private final ConnectionFactory connectionFactory;

    public RabbitMQConfig(AmqpAdmin amqpAdmin, ObjectMapper objectMapper, ConnectionFactory connectionFactory) {
        this.amqpAdmin = amqpAdmin;
        this.objectMapper = objectMapper;
        this.connectionFactory = connectionFactory;
    }


    @PostConstruct
    public void setup() {
        declareExchange();
        Reflections reflections = new Reflections("com");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        for (Class<?> clazz : annotatedClasses) {
            for (Method method : clazz.getDeclaredMethods()) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    Class<?> paramType = parameter.getType();
                    try {
                        DomainEvent eventInstance = (DomainEvent) paramType.getDeclaredConstructor().newInstance();
                        String queueName = format(clazz.getName());
                        System.out.println("Queue name: " + queueName);
                        String routingKey = eventInstance.eventName();
                        declareDurableQueues(queueName, routingKey);
                        declareListener(queueName, method.getName(), clazz, eventInstance.getClass());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (Exception error) {
                        System.out.println("Error configuring listener: " + error.getMessage());
                    }
                }
            }
        }
    }

    private void declareExchange() {
        amqpAdmin.declareExchange(new TopicExchange(exchangeName));
        amqpAdmin.declareExchange(new TopicExchange(retryExchangeName));
        amqpAdmin.declareExchange(new TopicExchange(deadLetterExchangeName));
    }

    private void declareDurableQueue(String queueName, String exchangeName, String routingKey) {
        amqpAdmin.declareQueue(new Queue(queueName, true, false, false));
        amqpAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null));
    }

    private void declareDurableRetryQueue(String queueName, String exchangeName, String routingKey) {
        HashMap<String, Object> arguments = retryQueueArguments(deadLetterExchangeName, routingKey);
        amqpAdmin.declareQueue(new Queue(queueName, true, false, false, arguments));
        amqpAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null));
    }

    private void declareDurableQueues(String queueName, String routingKey) {
        declareDurableQueue(queueName, exchangeName, routingKey);
        declareDurableRetryQueue(formatRetry(queueName), retryExchangeName, routingKey);
        declareDurableQueue(formatDeadLetter(queueName), deadLetterExchangeName, routingKey);
    }

    private void declareListener(String queueName, String methodName, Class<?> listenerClass, Class<? extends DomainEvent> eventClass) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        Object listenerInstance = createListenerBean(listenerClass);

        MessageListenerAdapter adapter = new MessageListenerAdapter(listenerInstance, methodName);
        adapter.setMessageConverter(new DomainEventMessageConverter(objectMapper, eventClass));

        container.setQueueNames(queueName);
        container.setMessageListener(adapter);
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
//        container.setErrorHandler(msg -> {
//            System.err.println("Error in listener: " + msg.getMessage());
//            // make ack
//
////            handleConsumptionError(msg, queueName);
//        });
        container.start();
    }

    private Object createListenerBean(Class<?> listenerClass) {
        try {
            return listenerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating listener bean", e);
        }
    }

    private HashMap<String, Object> retryQueueArguments(String exchangeName, String routingKey) {
        return new HashMap<>() {{
            put("x-dead-letter-exchange", exchangeName);
            put("x-dead-letter-routing-key", routingKey);
            put("x-message-ttl", 1000);
        }};
    }

}



