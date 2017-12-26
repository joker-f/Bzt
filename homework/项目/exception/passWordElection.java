package com.lanou3g.homework.exception;

import com.lanou3g.lianxi.exception.Exceptionz;
import com.lanou3g.lianxi.exception.NotNumException;

public class passWordElection {
    public static boolean detection(String password)throws Exceptionz {
        if (true){
            throw new NotNumException();
        }
        return true;
    }
}
