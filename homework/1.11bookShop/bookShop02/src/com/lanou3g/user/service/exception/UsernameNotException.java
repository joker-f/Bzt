package com.lanou3g.user.service.exception;

public class UsernameNotException extends LoginExceptioin {
    @Override
    public String getMessage() {
        System.out.println("-11-");
        return "�û����������";
    }
}
