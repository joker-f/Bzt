package com.lanou3g.homework.login;

import java.util.Scanner;

/**
 * 功能选择
 */
public class Function {

    public static void choice(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("提手功能:1查询天气,2查询手机号归属地,3查询手速游戏前十用户");
        switch (scanner.nextInt()){
            case 1:
                //天气查询

                break;
            case  2:
                //手机号归属地
                break;
            case 3:
                //游戏手速查询
                break;
        }
    }
}
