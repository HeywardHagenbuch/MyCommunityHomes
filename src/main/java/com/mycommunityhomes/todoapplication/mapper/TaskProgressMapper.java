package com.mycommunityhomes.todoapplication.mapper;


import com.mycommunityhomes.todoapplication.persistence.model.Task;
import com.mycommunityhomes.todoapplication.persistence.model.TaskStatus;
import com.mycommunityhomes.todoapplication.service.dto.TaskInProgress;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskProgressMapper implements IMapper<TaskInProgress, Task> {

	@Override
	public Task map(TaskInProgress in) {

		Task task = new Task();
		task.setTitle(in.getTitle());
		task.setDescription(in.getDescription());
		task.setCreatedDate(LocalDateTime.now());
		task.setEta(in.getEta());
		task.setFinished(false);
		task.setTaskStatus(TaskStatus.IN_PROGRESS);
		
		return task;
	}

}
