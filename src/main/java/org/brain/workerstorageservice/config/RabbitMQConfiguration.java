package org.brain.workerstorageservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {
    private final RabbitQueueProperties queueProperties;

    /**
     * spring bean to create rabbitmq queue
     **/
    @Bean
    public Queue queue() {
        return new Queue(queueProperties.moveQueueName(), true);
    }

    /**
     * define exchange
     **/
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(queueProperties.exchangeName());
    }

    /**
     * bind queue to exchange
     **/
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(queueProperties.routingKey());
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory, SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setObservationEnabled(true);  // Enable Micrometer Observation for RabbitMQ listeners
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    // Created automatically by autoconfig-> ConnectionFactory, RabbitTemplate, RabbitAdmin
}