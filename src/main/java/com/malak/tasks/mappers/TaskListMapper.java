package com.malak.tasks.mappers;

import com.malak.tasks.domain.dto.TaskListDto;
import com.malak.tasks.domain.entities.TaskList;

public interface TaskListMapper {
	TaskList fromDto(TaskListDto taskListDto);
	TaskListDto toDto(TaskList taskList);
}
