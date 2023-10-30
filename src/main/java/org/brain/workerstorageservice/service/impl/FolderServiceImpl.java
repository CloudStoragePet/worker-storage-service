package org.brain.workerstorageservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.brain.workerstorageservice.model.MoveFolderTask;
import org.brain.workerstorageservice.repository.FolderTaskRepository;
import org.brain.workerstorageservice.service.FolderService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    private final FolderTaskRepository folderTaskRepository;

    @Override
    public void moveFolder(MoveFolderTask moveFolderTask) {
        log.info("moveFolder: {}", moveFolderTask);
        moveFolderTask.setStatus("COMPLETED");
        folderTaskRepository.save(moveFolderTask);
        log.info("moveFolder task saved: {}", moveFolderTask);
    }
}
