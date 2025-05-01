package com.malak.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.repositories.TaskListRepository;
import com.malak.tasks.services.TaskListService;

import jakarta.transaction.Transactional;

@Service
public class TaskListServiceImpl implements TaskListService{
	
	private final TaskListRepository TaskListRepository;
	
	public TaskListServiceImpl(TaskListRepository taskListRepository) {
		this.TaskListRepository = taskListRepository;
	}
	
	@Override
	public List<TaskList> listTaskLists() {
		return TaskListRepository.findAll();
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
		return TaskListRepository.save(
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

	@Override
	public Optional<TaskList> getTaskList(UUID taskListId) {
		return TaskListRepository.findById(taskListId);
	}
	
	@Transactional
	@Override
	public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
		if(null == taskList.getId()) {
			throw new IllegalArgumentException("Task list must have an id");
		}
		if(!Objects.equals(taskListId, taskList.getId() )) {
			throw new IllegalArgumentException("Attempting to change task id is not permitted");
		}
		TaskList existingTaskList = TaskListRepository.findById(taskListId).orElseThrow(() -> 
			new IllegalArgumentException("Task list not found"));
		existingTaskList.setTitle(taskList.getTitle());
		existingTaskList.setDescription(taskList.getDescription());
		existingTaskList.setUpdated(LocalDateTime.now());
		
		return TaskListRepository.save(existingTaskList);
	}
	@Transactional
	@Override
	public void deleteTaskList(UUID taskListId) {
		TaskListRepository.deleteById(taskListId);
	}

}
