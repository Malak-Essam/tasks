package com.malak.tasks.mappers;

import com.malak.tasks.domain.dto.TaskDto;
import com.malak.tasks.domain.entities.Task;

public interface TaskMapper {
	Task fromDto(TaskDto taskDto);
	TaskDto toDto(Task task);
}
