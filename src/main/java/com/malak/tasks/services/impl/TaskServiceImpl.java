package com.malak.tasks.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.repositories.TaskRepository;
import com.malak.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	

	@Override
	public List<Task> listTasks(UUID taskListId) {
		return taskRepository.findByTaskListId(taskListId);
	}

}
