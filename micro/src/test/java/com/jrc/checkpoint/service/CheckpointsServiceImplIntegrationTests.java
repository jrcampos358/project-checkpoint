package com.jrc.checkpoint.service;

import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.repository.CheckpointsRepository;
import com.jrc.checkpoint.service.CheckpointsService;
import com.jrc.checkpoint.service.CheckpointsServiceImpl;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CheckpointsServiceImplIntegrationTests {

    @TestConfiguration
    static class CheckpointsServiceImplTestContextConfiguration {
        @Bean
        public CheckpointsService checkpointsService() {
            return new CheckpointsServiceImpl();
        }
    }

    @Autowired
    private CheckpointsService checkpointsService;

    @MockBean
    private CheckpointsRepository checkpointsRepository;

    @Test
    public void given2ProjectCheckpoints_whengetAll_thenReturn2Records() {
        UUID projectId = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
        Checkpoint checkpoint1 = new Checkpoint("Gather Requirements", projectId);
        Checkpoint checkpoint2 = new Checkpoint("Identify ExistingServices for Reuse", projectId);
        List<Checkpoint> allCheckpointByProjectId = Arrays.asList(checkpoint1, checkpoint2);

        doReturn(allCheckpointByProjectId).when(checkpointsRepository).findCheckpointsByProjectId(projectId);

        // Execute the service call
        List<Checkpoint> returnedCheckpoint = checkpointsService.getCheckpointsByProjectId(projectId);
        verifyFindAllEmployeesIsCalledOnce(projectId);
        assertThat(allCheckpointByProjectId).hasSize(2).extracting(Checkpoint::getName).contains(checkpoint1.getName(),
                checkpoint2.getName());
    }

    @Test
    public void givenNewProjectCheckpoint_whenSave_thenReturnnewRecordWithId() {
        UUID projectId = UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
        Checkpoint checkpoint1 = new Checkpoint("Identify ExistingServices for Reuse", projectId);
        Checkpoint checkpointMock = new Checkpoint("Identify ExistingServices for Reuse", projectId);

        doReturn(checkpointMock).when(checkpointsRepository).save(checkpoint1);

        // Execute the service call
        Checkpoint returnedCheckpoint = checkpointsService.saveOrUpdate(checkpoint1);
        assertThat(returnedCheckpoint).hasFieldOrPropertyWithValue("name", "Identify ExistingServices for Reuse");
    }

    private void verifyFindAllEmployeesIsCalledOnce(UUID projectId) {
        Mockito.verify(checkpointsRepository, VerificationModeFactory.times(1)).
                findCheckpointsByProjectId(projectId);
        Mockito.reset(checkpointsRepository);
    }
}
