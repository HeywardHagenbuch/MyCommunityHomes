package com.mycommunityhomes.todoapplication.mapper;

import com.mycommunityhomes.todoapplication.exceptions.TaskException;
import com.mycommunityhomes.todoapplication.persistence.repository.TaskRepository;
import com.mycommunityhomes.todoapplication.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TaskProgressMapperTest {

    private TaskRepository taskRepository;
    private TaskProgressMapper taskProgressMapper;
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskProgressMapper = Mockito.mock(TaskProgressMapper.class);
        taskService = new TaskService(taskRepository, taskProgressMapper);
    }

    @Test
    public void updateTaskAsFinished_shouldThrowTaskExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(TaskException.class, () -> taskService.updateTaskAsFinished(taskId));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    public void deleteTask_shouldThrowTaskExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(TaskException.class, () -> taskService.deleteTask(taskId));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    public void findTaskById_shouldThrowTaskExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(TaskException.class, () -> taskService.findTaskById(taskId));
        assertEquals("Task not found", exception.getMessage());
    }

}
