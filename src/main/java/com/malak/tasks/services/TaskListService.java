package com.malak.tasks.services;

import java.util.List;

import com.malak.tasks.domain.entities.TaskList;

public interface TaskListService {
	List<TaskList> listTaskLists();
	TaskList createTaskList(TaskList taskList);
}
