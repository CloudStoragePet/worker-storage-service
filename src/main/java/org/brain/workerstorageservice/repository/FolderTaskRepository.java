package org.brain.workerstorageservice.repository;


import org.brain.workerstorageservice.model.MoveFolderTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderTaskRepository extends CrudRepository<MoveFolderTask, String> {}
