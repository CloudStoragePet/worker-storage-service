package org.brain.workerstorageservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.brain.workerstorageservice.model.MoveFolderTask;
import org.brain.workerstorageservice.service.FolderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * StorageListener listens to the folder move task messages from RabbitMQ.
 * <p>
 * Expected message format (JSON):
 * {
 * "taskId": "uuid",
 * "sourceFolder": "/path/to/source",
 * "destinationFolder": "/path/to/destination",
 * "userId": 1
 * }
 * <p>
 * Operations:
 * 1. Deserialize the message JSON into a MoveFolderTask object.
 * 2. Perform the folder move operation.
 * 3. Store progress updates in Redis.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StorageListener {
    private final FolderService folderService;
    @KafkaListener(topics = "${spring.kafka.topicName}", groupId = "${spring.kafka.groupId}")
    public void consumeMoveFolder(MoveFolderTask moveFolderTask) {
        log.info("Message received: {}", moveFolderTask);
        folderService.moveFolder(moveFolderTask);
        log.info("Message processed: {}", moveFolderTask);
    }
}
