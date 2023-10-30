package org.brain.workerstorageservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public record BasePathProperties(String path) {
}
