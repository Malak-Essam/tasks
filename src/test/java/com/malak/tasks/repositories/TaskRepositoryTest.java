package com.malak.tasks.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.domain.entities.TaskPriority;
import com.malak.tasks.domain.entities.TaskStatus;

@DataJpaTest
public class TaskRepositoryTest {
	
	@Autowired
	private TaskRepository underTest;
	
	@Autowired
	private TaskListReopsitory taskListRepository;
	
	@Test
	void shouldReturnTasksByTaskListId() {
		//given
		LocalDateTime now = LocalDateTime.now();
		TaskList taskList = new TaskList(
				null,
				"task list",
				"task list description",
				null,
				now,
				now
				);
		Task taskOne = new Task(
				null,
				"task one",
				"task one description",
				now,
				TaskStatus.OPEN,
				TaskPriority.LOW,
				taskList,
				now,
				now);
		Task taskTwo = new Task(
				null,
				"task two",
				"task two description",
				now,
				TaskStatus.OPEN,
				TaskPriority.LOW,
				taskList,
				now,
				now);
		//when
		taskListRepository.save(taskList);
		underTest.save(taskOne);
		underTest.save(taskTwo);
		
		//then
		var result = underTest.findByTaskListId(taskList.getId());
		assertThat(result)
			.hasSize(2)
			.extracting(Task::getTitle)
			.containsExactlyInAnyOrder("task one", "task two");
	}
}
