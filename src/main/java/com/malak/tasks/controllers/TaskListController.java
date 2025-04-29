package com.malak.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malak.tasks.domain.dto.TaskListDto;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.mappers.TaskListMapper;
import com.malak.tasks.services.TaskListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/task-lists")
@Tag(name = "Task List Controller", description = "Manage tasklists: create, update, delete, and list tasklists")
public class TaskListController {
	private final TaskListService taskListService;
	private final TaskListMapper taskListMapper;
	
	
	public TaskListController(
			TaskListService taskListService, 
			TaskListMapper taskListMapper) {
		
		this.taskListService = taskListService;
		this.taskListMapper = taskListMapper;
	}
	
	@GetMapping
	@Operation(summary = "Get a list of tasklists", description = "Gets a list of tasklists")
	public List<TaskListDto> listTaskLists(){
		 return taskListService
				 .listTaskLists()
				 .stream()
				 .map(taskListMapper::toDto)
				 .toList();
	}
	
	@PostMapping
	@Operation(summary = "Create a new tasklist", description = "Adds a new tasklist")
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
		return taskListMapper.toDto(createdTaskList);
	}
	
	@GetMapping("/{task_list_id}")
	@Operation(summary = "Gets a tasklist", description = "Gets  a tasklist")
	public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
		Optional<TaskList> taskListOptional = taskListService.getTaskList(taskListId);
		return taskListOptional.map(taskListMapper::toDto);
	}
		
	@PutMapping("/{task_list_id}")
	@Operation(summary = "Update tasklist details", description = "Updates tasklist details")
	public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskListDto newtaskListDto){
		TaskList updatedTaskList = taskListService.updateTaskList(taskListId, taskListMapper.fromDto(newtaskListDto));
		return taskListMapper.toDto(updatedTaskList);
	}
	@DeleteMapping("/{task_list_id}")
	@Operation(summary = "Delete a tasklist", description = "Deletes a tasklist with all it's tasks")
	public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
		taskListService.deleteTaskList(taskListId);
	}
	
}
