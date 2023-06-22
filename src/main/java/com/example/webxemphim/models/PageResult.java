package com.example.webxemphim.models;

import java.util.List;

public class PageResult<T> {
    private List<T> content;
    private int totalPages;

    // Constructors, getters, setters

    public PageResult() {
    }

    public PageResult(List<T> content, int totalPages) {
        this.content = content;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
