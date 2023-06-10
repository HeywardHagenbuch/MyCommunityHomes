package com.mycommunityhomes.todoapplication.service.dto;

import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskInProgress {

    private String title;
    private String description;
    private LocalDateTime eta;
    private TaskStatus taskStatus;
}
