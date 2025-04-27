package com.malak.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.repositories.TaskListReopsitory;
import com.malak.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService{
	
	private final TaskListReopsitory taskListReopsitory;
	
	public TaskListServiceImpl(TaskListReopsitory taskListReopsitory) {
		this.taskListReopsitory = taskListReopsitory;
	}
	
	@Override
	public List<TaskList> listTaskLists() {
		return taskListReopsitory.findAll();
	}

	@Override
	public TaskList createTaskList(TaskList taskList) {
		if(taskList.getId() != null) {
			throw new IllegalArgumentException("Task list already has an id");
		}
		if(taskList.getTitle() == null || taskList.getTitle().isBlank()) {
			throw new IllegalArgumentException("Task list title must be present!");
		}
		LocalDateTime now = LocalDateTime.now();
		return taskListReopsitory.save(
				new TaskList(
						null,
						taskList.getTitle(), 
						taskList.getDescription(),
						null,
						now,
						now
					)
				);
	}

}
