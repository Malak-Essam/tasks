package com.malak.tasks.utils;

import java.time.LocalDateTime;

import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.domain.entities.TaskPriority;
import com.malak.tasks.domain.entities.TaskStatus;

public class TestDataFactory {
	
	static LocalDateTime now = LocalDateTime.now();
    public static TaskList createTaskList() {
        return new TaskList(
            null,
            "task list",
            "task list description",
            null,
            now,
            now
        );
    }

    public static Task createTask(String title, TaskList taskList) {
        return new Task(
            null,
            title,
            title + " description",
            now,
            TaskStatus.OPEN,
            TaskPriority.LOW,
            taskList,
            now,
            now
        );
    }
}
