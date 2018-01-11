package com.lanou3g.user.service;

import com.lanou3g.user.dao.UserDao;
import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.exception.*;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.UUID;

public class UserService {
    //里面写注册登录的逻辑判断
    private UserDao userDao=new UserDao();
    private SendEmail sendEmail=new SendEmail();
    //登录判断
    public User login(User user) throws LoginExceptioin {
        //查询到用户信息
        System.out.println("-7-"+user.getUsername());
        User fromDb = userDao.queryByUsernameAll(user.getUsername());
        System.out.println("-8-"+fromDb);
        //如果用户名为null的话则抛出异常
        if(fromDb==null){
            System.out.println("01");
            throw new InvalidUsernameException();
        }
        //如果没有在数据库中找到用户的话抛出异常
        if (!fromDb.getUsername().equals(user.getUsername())){
            System.out.println("02");
            throw new UsernameNotException();
        }
        //如果输入的密码与插叙的密码不相同抛出异常
        if (!fromDb.getPassword().equals(user.getPassword())){
            System.out.println("03");
            throw new PasswordNotException();
        }
        System.out.println("-2-");
        return fromDb;
    }
    //注册判断,先要判断用户名是否已经存在
    public void register(User user) throws RegisterExcepton, MessagingException {
        User formDb = userDao.queryByUsernameAll(user.getUsername());
        System.out.println("-207"+formDb);
        System.out.println();
        //用户已被注册
        if (formDb!=null){
            throw new RegisteredException();
        }
        String uid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUid(uid);
        //发送邮件给用户
        sendEmail.sendE(user.getEmail(),uid);


    }

    //添加用户
    public int addUser(User user) throws RegisterExcepton{
        int i = userDao.registerUser(user);
        System.out.println(i);
        if (i!=1){
            throw new RegisterExcepton();
        }
        return i;
    }

    //使用code(这个code是从页面中得到的)查询数据库得到user对象，如果数据库返回值为null，抛出异常
    // 根据uid，判断state的状态true；抛出异常，false次改用户的状态为true
    public User active(String code) throws RegisterExcepton {
        User user=new User();
        User byCode = userDao.findByCode(code);
        if (byCode!=null){
            throw new CodeException();
        }
        userDao.seletcUid(user.getUid(),false);
        return byCode;
    }
}
