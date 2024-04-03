package org.pratice.donemile.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestDTO {
    // field
    private int page;
    private int size;

    // constructor
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    // method
    public Pageable getPageable (Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
