package com.malak.tasks.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.malak.tasks.domain.entities.Task;
import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.utils.TestDataFactory;

@DataJpaTest
public class TaskRepositoryIntegrationTest {
	
	@Autowired
	private TaskRepository underTest;
	
	@Autowired
	private TaskListRepository taskListRepository;
	
	@Test
	void findByTaskListId_whenTaskListExist_returnListOfTasks() {
		//given
		TaskList taskList = TestDataFactory.createTaskList();
		Task taskOne = TestDataFactory.createTask("task one", taskList);
		Task taskTwo = TestDataFactory.createTask("task two", taskList);
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
	
	@Test
	void findByTaskListIdAndId_whenTaskExists_returnsMatchingTask() {
		//given
		TaskList taskList = TestDataFactory.createTaskList();
		Task task = TestDataFactory.createTask("task", taskList);
		//when
		taskListRepository.save(taskList);
		underTest.save(task);
		//then
		var result = underTest.findByTaskListIdAndId(taskList.getId(), task.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(task);
	}
	
	@Test
	void deleteByTaskListIdAndId_whenCalled_deletesTheTask() {
		//given
		TaskList taskList = TestDataFactory.createTaskList();
		Task task = TestDataFactory.createTask("task to delete", taskList);
		taskListRepository.save(taskList);
		underTest.save(task);
		
		//ensure the task saved
		assertThat(underTest.findByTaskListIdAndId(taskList.getId(), task.getId())).isPresent();
		
		//when
		underTest.deleteByTaskListIdAndId(taskList.getId(), task.getId());
		
		//then
		var result = underTest.findByTaskListIdAndId(taskList.getId(), task.getId());
		assertThat(result).isNotPresent();
	}
}
