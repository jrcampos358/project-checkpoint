package com.jrc.checkpoint.service;

import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.repository.CheckpointsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CheckpointsServiceImpl implements CheckpointsService {

    Logger logger = LogManager.getLogger(CheckpointsServiceImpl.class);

    @Autowired
    CheckpointsRepository checkpointsRepository;

    // Get all Checkpoint record by Project Id
    public List < Checkpoint > getCheckpointsByProjectId(UUID projectId) {
        List < Checkpoint > res = checkpointsRepository.findCheckpointsByProjectId(projectId);

        for (Checkpoint chkp: res) {
            int chkSum = 0;
            if (chkp.getTasks() != null && chkp.getTasks().size() > 0) {
                chkSum = chkp.getTasks().stream().mapToInt(task ->
                        task.getComplPercentage()).sum() / chkp.getTasks().size();

            }
            chkp.setComplPercentage(chkSum);
        }
        return res;
    }

    //saving a specific record by using the method save() of CrudRepository
    public Checkpoint saveOrUpdate(Checkpoint checkpoint) {
        return checkpointsRepository.save(checkpoint);
    }
}