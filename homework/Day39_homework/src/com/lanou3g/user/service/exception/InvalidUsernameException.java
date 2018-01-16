package com.lanou3g.user.service.exception;

public class InvalidUsernameException extends LoginExceptioin {
    @Override
    public String getMessage() {
        System.out.println("-8-");
        return "用户名储存在";
    }
}
