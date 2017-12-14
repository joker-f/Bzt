package com.lanou3g.homework.exception;

import com.lanou3g.lianxi.exception.Exceptionz;

public class notPsElement extends Exceptionz {
    @Override
    public String getMessage() {
        return "密码位数过少";
    }
}
