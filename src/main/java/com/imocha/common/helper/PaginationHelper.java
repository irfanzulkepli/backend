package com.imocha.common.helper;

import org.springframework.data.domain.Sort.Direction;

public class PaginationHelper {

    public static Direction getSortDirection(String sortDirection) {
        return (sortDirection.equalsIgnoreCase("desc")) ? Direction.DESC : Direction.ASC;
    }
}
