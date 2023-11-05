package org.brain.workerstorageservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.legacy.math.linearalgebra.IntegerFunctions;
import org.brain.workerstorageservice.exceptions.TaskCanceledException;
import org.brain.workerstorageservice.exceptions.TaskFailedException;
import org.brain.workerstorageservice.model.MoveFolderTask;
import org.brain.workerstorageservice.model.enums.MoveFolderTaskStatus;
import org.brain.workerstorageservice.repository.FolderTaskRepository;
import org.brain.workerstorageservice.service.FolderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    private final FolderTaskRepository folderTaskRepository;
    private static final int SLEEP_TIMEOUT = 5 * 1000; // 5 seconds
    private static final int STEP_UPDATE = 10;


    @Override
    public void moveFolder(MoveFolderTask moveFolderTask) {
        log.info("moveFolder: {}", moveFolderTask);

        moveFolderTask.setStatus(MoveFolderTaskStatus.IN_PROGRESS);
        updateFolderTask(moveFolderTask);

        try {
            executeHighCpuTask(moveFolderTask);
            moveFolderTask.setStatus(MoveFolderTaskStatus.COMPLETED);
        } catch (TaskCanceledException e) {
            log.info("moveFolder task canceled: {}", moveFolderTask);
        } catch (TaskFailedException e) {
            log.error("moveFolder task failed: {}", moveFolderTask, e);
            moveFolderTask.setStatus(MoveFolderTaskStatus.FAILED);
        } finally {
            updateFolderTask(moveFolderTask);
        }

        log.info("moveFolder task completed: {}", moveFolderTask);
    }


    @Override
    public void updateFolderTask(MoveFolderTask moveFolderTask) {
        folderTaskRepository.save(moveFolderTask);
    }


    @Override
    public Optional<MoveFolderTask> getMoveFolderTask(String id) {
        return folderTaskRepository.findById(id);
    }

    /**
     *  Plug for high CPU task
     *  Execute high CPU task, update progress and check status
     *
     * @param moveFolderTask - task to execute
     * @throws TaskCanceledException - if the task is canceled
     * @throws TaskFailedException - if the something went wrong with waiting for task status change
     */
    private void executeHighCpuTask(MoveFolderTask moveFolderTask) throws TaskCanceledException, TaskFailedException {
        Long complexity = moveFolderTask.getSourceFolderId(); // Adjust this value based on the desired task complexity
        int primeCount = 0;
        int num = 2;
        int nextProgressUpdate = STEP_UPDATE;
        while (primeCount < complexity) {
            if (IntegerFunctions.isPrime(num)) {
                primeCount++;
                int progress = (int) (((double) primeCount / complexity) * 100);
                if (progress >= nextProgressUpdate) {
                    // update progress and check status at every stepUpdate
                    moveFolderTask.setProgress(progress);
                    updateProgressAndCheckStatus(moveFolderTask.getId(), progress);
                    nextProgressUpdate += STEP_UPDATE;
                }
            }
            num++;
        }
    }


    /**
     * Update the progress of the task and check if the task is canceled or stopped.
     * If the task is canceled, throw TaskCanceledException.
     * If the task is stopped, sleep for 5 seconds and continue.
     *
     * @param id       - id of the task
     * @param progress - progress of the task (0-100)
     * @throws TaskCanceledException - if the task is canceled
     * @throws TaskFailedException   - if the something went wrong with waiting for task status change
     */
    private void updateProgressAndCheckStatus(String id, int progress) throws TaskCanceledException, TaskFailedException {
        while (true) {
            MoveFolderTask moveFolderTask = getMoveFolderTask(id)
                    .orElseThrow(() -> new TaskFailedException("Task not found: " + id));

            if (moveFolderTask.getStatus() == MoveFolderTaskStatus.CANCELED) {
                throw new TaskCanceledException();
            } else if (moveFolderTask.getStatus() == MoveFolderTaskStatus.STOPPED) {
                try {
                    Thread.sleep(SLEEP_TIMEOUT); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    throw new TaskFailedException();
                }
            } else {
                moveFolderTask.setProgress(progress);
                updateFolderTask(moveFolderTask);
                break;
            }
        }
    }
}
