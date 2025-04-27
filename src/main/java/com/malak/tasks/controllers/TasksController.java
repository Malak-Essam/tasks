package com.malak.tasks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malak.tasks.domain.dto.TaskDto;
import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.mappers.TaskMapper;
import com.malak.tasks.services.TaskService;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TasksController {
	
	private final TaskService taskService;
	private final TaskMapper taskMapper;
	
	public TasksController(TaskService taskService, TaskMapper taskMapper) {
		this.taskService = taskService;
		this.taskMapper = taskMapper;
	}
	
	@GetMapping
	public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
		return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
	}
	
	@PostMapping
	public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,
			@RequestBody TaskDto taskDto) {
		Task task = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
		return taskMapper.toDto(task);
	}
	@GetMapping("/{task_id}")
	public TaskDto getTask(@PathVariable("task_list_id") UUID taskListId,
			@PathVariable("task_id") UUID taskId) {
		return taskService.getTask(taskListId, taskId).map(taskMapper::toDto).orElse(null);
	}
	
}
