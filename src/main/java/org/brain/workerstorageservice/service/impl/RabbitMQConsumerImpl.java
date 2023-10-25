package org.brain.workerstorageservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.brain.workerstorageservice.service.RabbitMQConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQConsumerImpl implements RabbitMQConsumer {
    @RabbitListener(queues = "${rabbitmq.move-queue-name}")
    public void consume (String message) {
        log.info("Message received: {}", message);
    }
}
