package com.malak.tasks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
