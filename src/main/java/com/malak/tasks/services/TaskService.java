package com.malak.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.malak.tasks.domain.entities.Task;

public interface TaskService {
	List<Task> listTasks(UUID taskListId);
	Task createTask(UUID taskListId, Task task);
	Optional<Task> getTask(UUID taskListId, UUID taskId);
	Task updateTask(UUID taskListId, Task task, UUID taskId);
	void deleteTask(UUID taskListId, UUID taskId);
}
