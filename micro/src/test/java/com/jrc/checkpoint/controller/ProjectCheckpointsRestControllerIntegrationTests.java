package com.jrc.checkpoint.controller;

import com.jrc.checkpoint.CheckpointsApplication;
import com.jrc.checkpoint.JsonUtil;
import com.jrc.checkpoint.controller.ProjectCheckpointsController;
import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.service.CheckpointsService;
import com.jrc.checkpoint.service.TasksService;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;


// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProjectCheckpointsController.class)
public class ProjectCheckpointsRestControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CheckpointsService checkpointsService;

    @MockBean
    private TasksService tasksService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCheckpoint_thenCreateCheckpoint() throws Exception {
        UUID projectId = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
        Checkpoint checkpoint1 = new Checkpoint("Gather Requirements", projectId);
        given(checkpointsService.saveOrUpdate(Mockito.any())).willReturn(checkpoint1);

        mvc.perform(post("/checkpoint").contentType(MediaType.APPLICATION_JSON).
                content(JsonUtil.toJson(checkpoint1))).andExpect(status().isCreated()).
                andExpect(jsonPath("$.name", is("Gather Requirements")));
        verify(checkpointsService, VerificationModeFactory.times(1)).saveOrUpdate(Mockito.any());
        reset(checkpointsService);
    }
}
