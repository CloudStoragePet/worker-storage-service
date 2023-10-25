package org.brain.workerstorageservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbitmq")
public record RabbitQueueProperties(String moveQueueName, String exchangeName, String routingKey) {
}
