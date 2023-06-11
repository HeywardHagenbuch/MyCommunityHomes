package com.mycommunityhomes.todoapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Task validTask;

    @Before
    public void setUp() {
        validTask = Task.builder().id(1L)
                .title("Task1")
                .description("Task1 description")
                .createdDate(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(1))
                .finished(false)
                .taskStatus(TaskStatus.IN_PROGRESS)
                .build();
    }

    @Test
    public void getTaskById() throws Exception {
        given(taskService.findTaskById(anyLong())).willReturn(validTask);

        mockMvc.perform(get("/api/v1/tasks/" + validTask.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Task1")));
    }

    @Test
    public void handlePost() throws Exception {
        //Given
        Task task = validTask;
        task.setId(null);
        Task savedTask = Task.builder().id(1L).title("New Task").build();
        String taskJson = objectMapper.writeValueAsString(task);

        given(taskService.createTask(any())).willReturn(savedTask);

        //When - Then
        mockMvc.perform(post("/api/v1/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void handleUpdate() throws Exception {
        // Given
        Task task = validTask;
        task.setId(1L);
        String taskJson = objectMapper.writeValueAsString(task);

        doNothing().when(taskService).updateTaskAsFinished(anyLong());

        // When - Then
        mockMvc.perform(put("/api/v1/tasks/finish/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Task marked as finished."));
    }
}
