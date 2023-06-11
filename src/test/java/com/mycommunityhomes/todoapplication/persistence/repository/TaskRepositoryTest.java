package com.mycommunityhomes.todoapplication.persistence.repository;

import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test task");
        task.setDescription("Test description");
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(task);
    }

    @AfterEach
    public void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void testFindAllByTaskStatus() {
        List<Task> tasks = taskRepository.findAllByTaskStatus(TaskStatus.IN_PROGRESS);
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.get(0).getTaskStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    @Transactional
    public void testMarkTaskAsFinished() {
        taskRepository.markTaskAsFinished(TaskStatus.COMPLETED, task.getId());
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        Task updatedTask = taskRepository.findById(task.getId()).orElseThrow();
        assertThat(updatedTask.getTaskStatus()).isEqualTo(TaskStatus.COMPLETED);
        assertThat(updatedTask.getFinished()).isTrue();
    }
}
