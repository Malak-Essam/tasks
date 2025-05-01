package com.malak.tasks.services.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malak.tasks.repositories.TaskListRepository;
import com.malak.tasks.services.TaskListService;

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
	
	
	
}
