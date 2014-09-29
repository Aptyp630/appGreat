package com.example.models;

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

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
