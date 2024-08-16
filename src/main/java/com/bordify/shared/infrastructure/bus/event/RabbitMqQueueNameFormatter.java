package com.bordify.shared.infrastructure.bus.event;

public final class RabbitMqQueueNameFormatter {
//    public static String format(DomainEventSubscriberInformation information) {
//        return information.formatRabbitMqQueueName();
//    }
//
    public static String formatRetry(String queueName) {
        return String.format("retry.%s", format(queueName));
    }

    public static String formatDeadLetter(String queueName) {
        return String.format("dead_letter.%s", format(queueName));
    }

    public static String format(String fullyQualifiedClassName) {
        String className = extractClassName(fullyQualifiedClassName);
        String snakeCaseName = toSnakeCase(className);
        return snakeCaseName;
    }

    public static String extractClassName(String fullyQualifiedClassName) {
        int lastDotIndex = fullyQualifiedClassName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fullyQualifiedClassName.substring(lastDotIndex + 1);
        }
        return fullyQualifiedClassName;
    }

    private static String toSnakeCase(String className) {
        StringBuilder snakeCase = new StringBuilder();
        for (char c : className.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (snakeCase.length() > 0) {
                    snakeCase.append('_');
                }
                snakeCase.append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }


}

