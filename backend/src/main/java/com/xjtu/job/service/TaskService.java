package com.xjtu.job.service;

import com.xjtu.job.model.Task;
import com.xjtu.job.store.TaskStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    public TaskStore store;

    public Optional<Task> delete(Long id) {

    }
}


