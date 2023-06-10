package com.mycommunityhomes.todoapplication.controller;

import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.service.TaskService;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Task>> findAllTasks() {
        return new ResponseEntity<>(taskService.findAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable ("id") Long id) {
        return new ResponseEntity<>(taskService.findTaskById(id), HttpStatus.OK);
    }

    @GetMapping("/status/{status}/")
    public ResponseEntity<List<Task>> findAllByTaskStatus(@PathVariable("status") TaskStatus status) {
        return new ResponseEntity<>(taskService.findAllTasksByStatus(status), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskInProgress taskInProgress) {
        return new ResponseEntity<>(taskService.createTask(taskInProgress), HttpStatus.CREATED);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<String> markTaskAsFinished(@PathVariable("id") Long id) {
        taskService.updateTaskAsFinished(id);
        return ResponseEntity.ok("Task marked as finished.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}
