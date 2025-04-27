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
import com.malak.tasks.repositories.TaskListReopsitory;
import com.malak.tasks.services.TaskListService;

@RestController
@RequestMapping(path = "/api/task-lists")
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
	public List<TaskListDto> listTaskLists(){
		 return taskListService
				 .listTaskLists()
				 .stream()
				 .map(taskListMapper::toDto)
				 .toList();
	}
	
	@PostMapping
	public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
		TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
		return taskListMapper.toDto(createdTaskList);
	}
	
	@GetMapping("/{id}")
	public Optional<TaskListDto> getTaskList(@PathVariable UUID id){
		Optional<TaskList> taskListOptional = taskListService.getTaskList(id);
		return taskListOptional.map(taskListMapper::toDto);
	}
		
	@PutMapping("/{id}")
	public TaskListDto updateTaskList(@PathVariable UUID id, @RequestBody TaskListDto newtaskListDto){
		TaskList updatedTaskList = taskListService.updateTaskList(id, taskListMapper.fromDto(newtaskListDto));
		return taskListMapper.toDto(updatedTaskList);
	}
	@DeleteMapping("/{id}")
	public void deleteTaskList(@PathVariable UUID id) {
		taskListService.deleteTaskList(id);
	}
	
}
