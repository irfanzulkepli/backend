package com.imocha.common.model;

import org.springframework.data.domain.Sort.Direction;

import lombok.Data;

@Data
public class PageableRequest {

    private int page;
    private int size;
    private Direction direction;
    private String[] properties;
}
