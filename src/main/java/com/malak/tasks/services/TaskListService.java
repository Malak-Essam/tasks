package com.malak.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.malak.tasks.domain.entities.TaskList;

public interface TaskListService {
	List<TaskList> listTaskLists();
	TaskList createTaskList(TaskList taskList);
	Optional<TaskList> getTaskList(UUID id);
	TaskList updateTaskList(UUID id, TaskList taskList);
	void deleteTaskList(UUID id);
}
