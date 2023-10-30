package org.brain.workerstorageservice.repository;

import org.brain.workerstorageservice.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    boolean existsByParent_IdAndName(Long id, String name);
}
