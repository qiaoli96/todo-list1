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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> deletedTask = taskService.delete(id);
        if (deletedTask.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
