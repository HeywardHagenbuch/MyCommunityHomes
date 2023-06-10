package com.mycommunityhomes.todoapplication.service;

import com.mycommunityhomes.todoapplication.exceptions.TaskException;
import com.mycommunityhomes.todoapplication.mapper.TaskProgressMapper;
import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.persistence.repository.TaskRepository;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskProgressMapper mapper;

    public TaskService (TaskRepository taskRepository, TaskProgressMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public List<Task> findAllTasks() {
        return this.taskRepository.findAll();
    }

    public List<Task> findAllTasksByStatus(TaskStatus status) {
        return this.taskRepository.findAllByTaskStatus(status);
    }

    public Task createTask(TaskInProgress taskInProgress) {
        Task task = mapper.map(taskInProgress);
        return this.taskRepository.save(task);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    @Transactional
    public void updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.updateTaskStatus(id, status);
    }

    public void deleteTask(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }

    public Task findTaskById() {
        return this.taskRepository.findById(1L)
                .orElseThrow(() -> new TaskException("Task not found", HttpStatus.NOT_FOUND));
    }
}
