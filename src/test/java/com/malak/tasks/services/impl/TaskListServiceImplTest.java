package com.malak.tasks.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malak.tasks.domain.entities.TaskList;
import com.malak.tasks.repositories.TaskListRepository;
import com.malak.tasks.utils.TestDataFactory;

@ExtendWith(MockitoExtension.class)
public class TaskListServiceImplTest {
	@Mock
	private TaskListRepository taskListRepository;
	
	@InjectMocks
	private TaskListServiceImpl underTest;
	
	@Test
	void verify_listTaskLists() {
		//when
		underTest.listTaskLists();
		//then
		verify(taskListRepository).findAll();
	}
	
	@Test
	void verify_createTaskList_works() {
	    // given 
	    var taskList = TestDataFactory.createTaskList();

	    // when
	    underTest.createTaskList(taskList);

	    // then
	    ArgumentCaptor<TaskList> captor = ArgumentCaptor.forClass(TaskList.class);
	    verify(taskListRepository).save(captor.capture());
	    TaskList saved = captor.getValue();

	    assertThat(saved.getId()).isNull();
	    assertThat(saved.getTitle()).isEqualTo(taskList.getTitle());
	    assertThat(saved.getDescription()).isEqualTo(taskList.getDescription());
	}
	
	@Test
	void createTaskList_shouldThrowIfTitleIsNullOrBlank() {
	    // null title
	    TaskList taskListWithNullTitle = TestDataFactory.createTaskList();
	    taskListWithNullTitle.setTitle(null);

	    assertThatThrownBy(() -> underTest.createTaskList(taskListWithNullTitle))
	        .isInstanceOf(IllegalArgumentException.class)
	        .hasMessage("Task list title must be present!");

	    // blank title
	    TaskList taskListWithBlankTitle = TestDataFactory.createTaskList();
	    taskListWithBlankTitle.setTitle("  ");

	    assertThatThrownBy(() -> underTest.createTaskList(taskListWithBlankTitle))
	        .isInstanceOf(IllegalArgumentException.class)
	        .hasMessage("Task list title must be present!");
	}
	
	
}
