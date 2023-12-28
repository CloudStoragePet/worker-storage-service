package org.brain.workerstorageservice;

import org.brain.workerstorageservice.config.BasePathProperties;
import org.brain.workerstorageservice.config.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BasePathProperties.class, KafkaProperties.class})
public class WorkerStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerStorageServiceApplication.class, args);
    }

}
