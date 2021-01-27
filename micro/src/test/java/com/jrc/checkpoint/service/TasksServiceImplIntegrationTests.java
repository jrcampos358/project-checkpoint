package com.jrc.checkpoint.service;

import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.model.Task;
import com.jrc.checkpoint.repository.TasksRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class TasksServiceImplIntegrationTests {

    @TestConfiguration
    static class TasksServiceImplTestContextConfiguration {
        @Bean
        public TasksService tasksService() {
            return new TasksServiceImpl();
        }
    }

    @Autowired
    private TasksService tasksService;

    @MockBean
    private TasksRepository tasksRepository;

    @Test
    public void givenNewTaskForACheckpoint_whenSave_thenReturnnewRecordWithId() {
        UUID checkpointId = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
        Task task = new Task("Tasks 1", 50, checkpointId);
        Task taskMock = new Task("Tasks 1", 50, checkpointId);

        doReturn(taskMock).when(tasksRepository).save(task);

        // Execute the service call
        Task returnedTask = tasksService.saveOrUpdate(task);
        assertThat(returnedTask).hasFieldOrPropertyWithValue("text", "Tasks 1");
    }
}
