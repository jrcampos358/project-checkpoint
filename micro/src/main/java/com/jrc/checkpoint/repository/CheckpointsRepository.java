package com.jrc.checkpoint.repository;
import com.jrc.checkpoint.model.Checkpoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//repository that extends CrudRepository
public interface CheckpointsRepository extends CrudRepository <Checkpoint, UUID > {

    @Query("from Checkpoint c where c.projectId = :id")
    List<Checkpoint> findCheckpointsByProjectId(@Param("id") UUID id);
}