# we need to create a construct by default without arguments. We need this because we use reflections to find all the classes annotated with @DomainEventSubscriber and to create a intence.

    @NoArgsConstructor --> this method of lombok creates a constructor without arguments

```java
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

```

#### Note the following method:

```java
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
//                        declareDurableQueues(queueName, routingKey);
//                        declareListener(queueName, method.getName(), clazz, eventInstance.getClass());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                        throw new RuntimeException(e);
                        System.out.println("Error configuring listener: " + e.getMessage());
                    } catch (Exception error) {
                        System.out.println("Error configuring listener: " + error.getMessage());
                    }
                }
            }
        }
    }
```

You can see that we are using reflections to find all the classes annotated with @DomainEventSubscriber. We are then iterating over the methods of each class and we are trying to create an instance of the class using the default constructor. If we can't create an instance, we are throwing an exception. 

```java
    private Object createListenerBean(Class<?> listenerClass) {
        try {
            return listenerClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating listener bean", e);
        }
    }
```
