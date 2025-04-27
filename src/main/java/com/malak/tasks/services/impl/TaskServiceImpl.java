package com.malak.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.domain.entities.TaskPriority;
import com.malak.tasks.domain.entities.TaskStatus;
import com.malak.tasks.repositories.TaskListReopsitory;
import com.malak.tasks.repositories.TaskRepository;
import com.malak.tasks.services.TaskService;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final TaskListReopsitory taskListRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository,
			TaskListReopsitory taskListRepository) {
		this.taskRepository = taskRepository;
		this.taskListRepository = taskListRepository;
	}
	

	@Override
	public List<Task> listTasks(UUID taskListId) {
		return taskRepository.findByTaskListId(taskListId);
	}


	@Override
	public Task createTask(UUID taskListId, Task task) {
		if(task.getId() != null) {
			throw new IllegalArgumentException("task already has an id "+ task.getId());
		}
		if(task.getTitle().isBlank() || task.getTitle() == null) {
			throw new IllegalArgumentException("Task title must be present!");
		}
		TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
				.orElse(TaskPriority.MEDIUM);
		TaskStatus taskStatus = TaskStatus.OPEN;
		
		TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> 
			new IllegalArgumentException("Invalid task list id"));
		
		LocalDateTime now = LocalDateTime.now();
		Task taskToSave = new Task(
				null,
				task.getTitle(),
				task.getDescription(),
				task.getDueDate(),
				taskStatus,
				taskPriority,
				taskList,
				now,
				now);
		
		return taskRepository.save(taskToSave);
		
	}


	@Override
	public Optional<Task> getTask(UUID taskListId, UUID taskId) {
		return taskRepository.findByTaskListIdAndId(taskListId, taskId);
	}


	@Override
	public Task updateTask(UUID taskListId, Task task, UUID taskId) {
		if(task.getId() == null) {
			throw new IllegalArgumentException("task id can't be null");
		}
		if(!Objects.equals(task.getId(), taskId)) {
			throw new IllegalArgumentException("Attempting to change task id is not permitted");
		}
		if(task.getStatus() == null) {
			throw new IllegalArgumentException("Status can not be null");
		}
		if(task.getPriority() == null) {
			throw new IllegalArgumentException("Priority can not be null");
		}
		Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() -> 
			new IllegalArgumentException("Task list not found"));
		
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus());
		existingTask.setUpdated(LocalDateTime.now());
		
		return taskRepository.save(existingTask);
	}

	@Transactional
	@Override
	public void deleteTask(UUID taskListId, UUID taskId) {
		taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
	}

}
