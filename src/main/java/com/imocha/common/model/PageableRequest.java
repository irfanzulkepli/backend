package com.imocha.common.model;

import org.springframework.data.domain.Sort.Direction;

import lombok.Data;

@Data
public class PageableRequest {
	private int page = 0;
	private int size = 20;
	private Direction direction = Direction.ASC;
	private String[] properties = { "id" };
}
