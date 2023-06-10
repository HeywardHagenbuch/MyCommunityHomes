package com.mycommunityhomes.todoapplication.controller;

import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.service.TaskService;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
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
    public Task findTaskById(@PathVariable String id) {
        return this.taskService.findTaskById();
    }

    @GetMapping("/status/{status}/")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllTasksByStatus(status);
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInProgress taskInProgress) {
        return this.taskService.createTask(taskInProgress);
    }

    @PatchMapping("/mark_as_finished/{id}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
    }

    @PatchMapping("/update_status/{id}/{status}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void updateTaskStatus(@PathVariable("id") Long id, @PathVariable("status") TaskStatus status) {
        this.taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        this.taskService.deleteTask(id);
    }
}
