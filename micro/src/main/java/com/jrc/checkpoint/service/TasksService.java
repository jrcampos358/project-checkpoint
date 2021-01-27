package com.jrc.checkpoint.service;

import com.jrc.checkpoint.model.Task;


public interface TasksService {

    //saving a specific record by using the method save() of CrudRepository
    public Task saveOrUpdate(Task task);
}