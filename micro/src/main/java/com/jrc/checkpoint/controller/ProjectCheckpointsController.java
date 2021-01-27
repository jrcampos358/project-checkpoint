package com.jrc.checkpoint.controller;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.model.Task;
import com.jrc.checkpoint.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jrc.checkpoint.service.CheckpointsService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProjectCheckpointsController {

    Logger logger = LogManager.getLogger(ProjectCheckpointsController.class);

    //autowire the CheckpointsService class
    @Autowired
    CheckpointsService checkpointsService;

    @Autowired
    TasksService tasksService;

    //creating a get mapping that retrieves the detail of a specific item
    @GetMapping("/checkpoint/{projectId}")
    private ResponseEntity<List<Checkpoint>> getCheckpoint(@PathVariable("projectId") UUID id, HttpServletResponse response) {
        try {
            List<Checkpoint> res = checkpointsService.getCheckpointsByProjectId(id);

            if (res == null || res.size() <= 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project Not Found", null);
            }

            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK);
        } catch (java.util.NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Project Not Found", e);
        }
    }

    //creating post mapping that post the detail in the database
    @PostMapping("/checkpoint")
    private ResponseEntity<Checkpoint> saveCheckpoint(@RequestBody Checkpoint checkpoint) {
        Checkpoint newChkpt = checkpointsService.saveOrUpdate(checkpoint);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newChkpt.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(newChkpt);
    }

    //creating post mapping that post the detail in the database
    @PostMapping("/task")
    private ResponseEntity<Task> saveCheckpointTask(@RequestBody Task task) {
        Task newTask = tasksService.saveOrUpdate(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTask.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(newTask);
    }
}