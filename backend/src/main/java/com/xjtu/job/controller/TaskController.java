package com.xjtu.job.controller;
import com.xjtu.job.model.Task;
import com.xjtu.job.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/tasks" )
public class TaskController {
    @Autowired
    public TaskService taskService;

    @GetMapping(produces = "application/json")
    public List<Task> list() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable Long id) {
        return ResponseEntity.of(taskService.find(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        taskService.saveTask(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/tasks/{id}")
                .buildAndExpand(task.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> deletedTask = taskService.delete(id);
        if (deletedTask.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
