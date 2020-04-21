package com.xjtu.job.controller;
import com.xjtu.job.model.Task;
import com.xjtu.job.service.TaskService;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    private List<Task> tasks = new ArrayList<Task>();

    @BeforeEach
    void setUp() {
        tasks.add(new Task(1L, "a"));
    }

    @Test
    public void shouldDeleteWhenExist() throws Exception {
        when(service.delete(2L)).thenReturn(Optional.of(new Task(2L, "B")));
        this.mockMvc.perform(delete("/api/tasks/2")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFoundWhenDeleteIfNotPresent() throws Exception {
        when(service.delete(2L)).thenReturn(Optional.empty());
        this.mockMvc.perform(delete("/api/tasks/2")).andDo(print()).andExpect(status().isNotFound());
    }
}