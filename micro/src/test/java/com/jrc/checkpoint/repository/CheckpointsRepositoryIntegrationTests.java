package com.jrc.checkpoint.repository;

import com.jrc.checkpoint.CheckpointsApplication;
import com.jrc.checkpoint.SpringTestConfiguration;
import com.jrc.checkpoint.model.Checkpoint;
import com.jrc.checkpoint.repository.CheckpointsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.*;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringTestConfiguration.class })
@DataJpaTest
public class CheckpointsRepositoryIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CheckpointsRepository checkpointsRepository;

    // write test cases here
    @Test
    public void givenACorrectSetup_thenAnEntityManagerWillBeAvailable() {
        assertNotNull(entityManager);
    }

    @Test
    public void whenfindCheckpointsByProjectId_thenReturnListOfCheckPoints() {
        // given
        UUID uuid=UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db");
        Checkpoint checkpoint = new Checkpoint("Chris Smith", uuid);
        entityManager.persist(checkpoint);
        entityManager.flush();

        // when
        List<Checkpoint> cps = checkpointsRepository.findCheckpointsByProjectId(uuid);

        // then
        assertThat(cps.size()).isEqualTo(1);
    }
}