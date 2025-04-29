package com.malak.tasks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malak.tasks.domain.dto.TaskDto;
import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.mappers.TaskMapper;
import com.malak.tasks.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
@Tag(name = "Task Controller", description = "Manage tasks: create, update, delete, and list tasks")
public class TasksController {
	
	private final TaskService taskService;
	private final TaskMapper taskMapper;
	
	public TasksController(TaskService taskService, TaskMapper taskMapper) {
		this.taskService = taskService;
		this.taskMapper = taskMapper;
	}
	
	@GetMapping
	@Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
	public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
		return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
	}
	
	@PostMapping
	@Operation(summary = "Create a new task", description = "Adds a new task to the tasklist")
	public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId,
			@RequestBody TaskDto taskDto) {
		Task task = taskService.createTask(taskListId, taskMapper.fromDto(taskDto));
		return taskMapper.toDto(task);
	}
	
	@GetMapping("/{task_id}")
	@Operation(summary = "get a task", description = "Gets a task from a list of tasks in a tasklist")
	public TaskDto getTask(@PathVariable("task_list_id") UUID taskListId,
			@PathVariable("task_id") UUID taskId) {
		return taskService.getTask(taskListId, taskId).map(taskMapper::toDto).orElse(null);
	}
	@PutMapping("/{task_id}")
	@Operation(summary = "Update a task", description = "Updates a task details")
	public TaskDto updateTask(
			@PathVariable("task_list_id") UUID taskListId,
			@PathVariable("task_id") UUID taskId,
			@RequestBody TaskDto taskDto) {
		Task updatedTask = taskService.updateTask(taskListId, taskMapper.fromDto(taskDto), taskId);
		return taskMapper.toDto(updatedTask);
	}
	
	@DeleteMapping("/{task_id}")
	@Operation(summary = "Delete a task", description = "Delete a task from tasklist")
	public void deleteTask(
			@PathVariable("task_list_id") UUID taskListId,
			@PathVariable("task_id") UUID taskId
			) {
		taskService.deleteTask(taskListId, taskId);
	}
	
}
