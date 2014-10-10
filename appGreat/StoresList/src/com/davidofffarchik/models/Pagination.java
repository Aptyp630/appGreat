package com.davidofffarchik.models;

public class Pagination {

    private int totalPage;
    private int currentPage;
    private int perPage;

    public Pagination(int totalPage, int currentPage, int perPage) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.perPage = perPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPerPage() {
        return perPage;
    }
}
