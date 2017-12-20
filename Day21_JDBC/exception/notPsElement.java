package com.lanou3g.homework01.exception;



public class notPsElement extends Exception{
    @Override
    public String getMessage() {
        return "密码位数过少";
    }
}
