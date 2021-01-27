package com.jrc.checkpoint.service;
import java.util.*;

import com.jrc.checkpoint.model.Checkpoint;


public interface CheckpointsService {

    // Get all Checkpoint record by Project Id
    public List < Checkpoint > getCheckpointsByProjectId(UUID projectId);

    // Saving a specific record by using the method save() of CrudRepository
    public Checkpoint saveOrUpdate(Checkpoint checkpoint);
}