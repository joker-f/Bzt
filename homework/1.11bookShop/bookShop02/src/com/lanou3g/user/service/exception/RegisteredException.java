package com.lanou3g.user.service.exception;

public class RegisteredException extends RegisterExcepton {
    @Override
    public String getMessage() {
        System.out.println("-10-");
        return "用户已被注册";
    }
}
