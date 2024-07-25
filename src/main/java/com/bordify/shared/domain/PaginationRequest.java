package com.bordify.shared.domain;

import lombok.Data;


@Data
public class PaginationRequest {

    private int pageNumber;
    private int pageSize;
    public PaginationRequest(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    // we do this because we want that the pagination start from index 0, but for our clients
    // our pagination start from 1 --> page = 1
    public int getPageNumber() {
        return pageNumber - 1;
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

}

