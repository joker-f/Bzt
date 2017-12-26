package com.lanou3g.homework;

import org.junit.Test;

public class Demo {
    @Test
    public static void addUser(){
        Test t=new Test();
        System.out.println("请输入用户名,");
        //增
//        method.add(new Utils.User(null,"李四",30,"北京"));
        //改
//        method.update(new Utils.User("37","王五",30,"南京"));
        //查找所有
//        List<Utils.User> users = method.queryall();
//        System.out.println(users);
        //根据id查找
//        Utils.User user = method.queryById("37");
//        System.out.println(user);
        //删
        t.delete("王五");
    }
}
