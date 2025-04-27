package com.malak.tasks.domain.dto;

import java.util.List;
import java.util.UUID;


public record TaskListDto(
		UUID id,
		String title,
		String descrition,
		Integer count,
		Double progress,
		List<TaskDto> tasks
		) {

}
