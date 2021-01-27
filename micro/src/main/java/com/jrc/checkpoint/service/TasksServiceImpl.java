package com.jrc.checkpoint.service;

import com.jrc.checkpoint.model.Task;
import com.jrc.checkpoint.repository.TasksRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {

    Logger logger = LogManager.getLogger(TasksServiceImpl.class);

    @Autowired
    TasksRepository tasksRepository;

    //saving a specific record by using the method save() of CrudRepository
    public Task saveOrUpdate(Task task) {
        return tasksRepository.save(task);
    }
}