package com.malak.tasks.services;

import java.util.List;
import java.util.UUID;

import com.malak.tasks.domain.entities.Task;

public interface TaskService {
	List<Task> listTasks(UUID taskListId);
}
