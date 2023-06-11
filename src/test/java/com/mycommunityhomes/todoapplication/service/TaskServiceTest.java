package com.mycommunityhomes.todoapplication.service;

import com.mycommunityhomes.todoapplication.exceptions.TaskException;
import com.mycommunityhomes.todoapplication.mapper.TaskProgressMapper;
import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.persistence.repository.TaskRepository;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTest {
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
    public void findAllTasks_shouldReturnAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.findAllTasks();
        assertEquals(tasks, result);
    }

    @Test
    public void findAllTasksByStatus_shouldReturnAllTasksWithGivenStatus() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAllByTaskStatus(TaskStatus.COMPLETED)).thenReturn(tasks);
        List<Task> result = taskService.findAllTasksByStatus(TaskStatus.COMPLETED);
        assertEquals(tasks, result);
    }

    @Test
    public void createTask_shouldSaveAndReturnNewTask() {
        TaskInProgress taskInProgress = new TaskInProgress();
        Task task = new Task();
        when(taskProgressMapper.map(taskInProgress)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.createTask(taskInProgress);
        assertEquals(task, result);
    }

    @Test
    public void updateTaskAsFinished_shouldMarkTaskAsFinished() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));
        taskService.updateTaskAsFinished(taskId);
        verify(taskRepository).markTaskAsFinished(eq(TaskStatus.COMPLETED), eq(taskId));
    }

    @Test
    public void updateTaskAsFinished_shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(TaskException.class, () -> taskService.updateTaskAsFinished(taskId));
    }

    @Test
    public void deleteTask_shouldDeleteTask() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));
        taskService.deleteTask(taskId);
        verify(taskRepository).deleteById(taskId);
    }

    @Test
    public void deleteTask_shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(TaskException.class, () -> taskService.deleteTask(taskId));
    }

    @Test
    public void findTaskById_shouldReturnTask() {
        Long taskId = 1L;
        Task task = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        Task result = taskService.findTaskById(taskId);
        assertEquals(task, result);
    }

    @Test
    public void findTaskById_shouldThrowExceptionWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(TaskException.class, () -> taskService.findTaskById(taskId));
    }
}
