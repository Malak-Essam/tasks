package com.malak.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.malak.tasks.domain.dto.TaskListDto;
import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.domain.entities.TaskStatus;
import com.malak.tasks.mappers.TaskListMapper;
import com.malak.tasks.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper{
	
	private final TaskMapper taskMapper;
	
	public TaskListMapperImpl(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}

	@Override
	public TaskList fromDto(TaskListDto taskListDto) {
		return new TaskList(
				taskListDto.id(),
				taskListDto.title(), 
				taskListDto.descrition(), 
				Optional.ofNullable(taskListDto.tasks())
			    .map(tasks -> tasks.stream()
			    	.map(taskMapper::fromDto)
			    	.toList()
			    ).orElse(null),
				null,
				null
				);
	}


	@Override
	public TaskListDto toDto(TaskList taskList) {
		return new TaskListDto(
				taskList.getId(),
				taskList.getTitle(),
				taskList.getDescription(), 
				Optional.ofNullable(taskList.getTasks())
					.map(List::size)
					.orElse(0),
				calculateTaskListProgress(taskList.getTasks()),
				Optional.ofNullable(taskList.getTasks())
					.map(tasks -> tasks.stream()
							.map(taskMapper::toDto).toList())
				.orElse(null)
					);
	}
	private Double calculateTaskListProgress(List<Task> tasks) {
		if(tasks == null) return null;
		long closedTaskCount = tasks.stream().filter(task ->
        	TaskStatus.CLOSED == task.getStatus()
					).count();
		
		return (double) closedTaskCount / tasks.size();
	}

}
