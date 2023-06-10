package com.mycommunityhomes.todoapplication.controller;

import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.service.TaskService;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks/")
public class TaskController {

    private final TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> findAllTasks() {
        return this.taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable Long id) {
        return this.taskService.findTaskById(id);
    }

    @GetMapping("/status/{status}/")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllTasksByStatus(status);
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInProgress taskInProgress) {
        return this.taskService.createTask(taskInProgress);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<String> markTaskAsFinished(@PathVariable("id") Long id) {
        taskService.updateTaskAsFinished(id);
        return ResponseEntity.ok("Task marked as finished.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        this.taskService.deleteTask(id);
    }
}
