package com.malak.tasks.mappers.impl;

import org.springframework.stereotype.Component;

import com.malak.tasks.domain.dto.TaskDto;
import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.mappers.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper{

	@Override
	public Task fromDto(TaskDto taskDto) {
		return new Task(
				taskDto.id(),
				taskDto.title(),
				taskDto.description(),
				taskDto.dueDate(),
				taskDto.status(),
				taskDto.priority(),
				null,
				null,
				null
				);
	}

	@Override
	public TaskDto toDto(Task task) {
		return new TaskDto(
				task.getId(),
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				task.getPriority(),
				task.getStatus()
				);
	}
	
}
