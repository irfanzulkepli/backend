package com.imocha.common.model;

import java.util.List;

import lombok.Data;

@Data
public class PageResponseModel {

    private List<Object> payload;
    private long totalSize;
    private int currentPage;
    private int limit;
}
