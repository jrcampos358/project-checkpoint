package com.jrc.checkpoint.repository;
import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

//repository that extends CrudRepository
public interface TasksRepository extends CrudRepository <Task, UUID> {}