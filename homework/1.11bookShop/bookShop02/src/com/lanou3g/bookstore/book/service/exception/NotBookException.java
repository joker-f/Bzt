package com.lanou3g.bookstore.book.service.exception;

public class NotBookException extends BookException {
    @Override
    public String getMessage() {
        return "没有找到此图书";
    }
}
