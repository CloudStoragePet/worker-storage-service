package org.brain.workerstorageservice;

import org.brain.workerstorageservice.config.BasePathProperties;
import org.brain.workerstorageservice.config.RabbitQueueProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BasePathProperties.class, RabbitQueueProperties.class})
public class WorkerStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerStorageServiceApplication.class, args);
    }

}
