package org.brain.workerstorageservice.service;

import org.brain.workerstorageservice.model.MoveFolderTask;

import java.util.Optional;

public interface FolderService {
    /**
     * Move folder task
     * <p>
     * 1. Set task status to IN_PROGRESS <br>
     * 2. Execute high CPU task <br>
     * 3. Set task status to COMPLETED <br>
     * 4. If task is canceled, set task status to CANCELED <br>
     * 5. If task is failed, set task status to FAILED <br>
     * 6. Update task progress in Redis <br>
     * </p>
     *
     * @param moveFolderTask - task to execute
     */
    void moveFolder(MoveFolderTask moveFolderTask);
    /**
     * Update MoveFolderTask
     * @param moveFolderTask - task to update
     */
    void updateFolderTask(MoveFolderTask moveFolderTask);
    /**
     * Get MoveFolderTask by id
     * @param id - id of the MoveFolderTask
     * @return MoveFolderTask
     */
    Optional<MoveFolderTask> getMoveFolderTask(String id);
}
