package com.bookstore.book.utils.enums;

public enum Status {
    create("Created"),
    complete("Completed"),
    cancel("Cancelled");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }



}
