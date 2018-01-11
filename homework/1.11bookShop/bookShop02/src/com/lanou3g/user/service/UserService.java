package com.lanou3g.user.service;

import com.lanou3g.user.dao.UserDao;
import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.exception.*;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.UUID;

public class UserService {
    //����дע���¼���߼��ж�
    private UserDao userDao=new UserDao();
    private SendEmail sendEmail=new SendEmail();
    //��¼�ж�
    public User login(User user) throws LoginExceptioin {
        //��ѯ���û���Ϣ
        System.out.println("-7-"+user.getUsername());
        User fromDb = userDao.queryByUsernameAll(user.getUsername());
        System.out.println("-8-"+fromDb);
        //����û���Ϊnull�Ļ����׳��쳣
        if(fromDb==null){
            System.out.println("01");
            throw new InvalidUsernameException();
        }
        //���û�������ݿ����ҵ��û��Ļ��׳��쳣
        if (!fromDb.getUsername().equals(user.getUsername())){
            System.out.println("02");
            throw new UsernameNotException();
        }
        //���������������������벻��ͬ�׳��쳣
        if (!fromDb.getPassword().equals(user.getPassword())){
            System.out.println("03");
            throw new PasswordNotException();
        }
        System.out.println("-2-");
        return fromDb;
    }
    //ע���ж�,��Ҫ�ж��û����Ƿ��Ѿ�����
    public void register(User user) throws RegisterExcepton, MessagingException {
        User formDb = userDao.queryByUsernameAll(user.getUsername());
        System.out.println("-207"+formDb);
        System.out.println();
        //�û��ѱ�ע��
        if (formDb!=null){
            throw new RegisteredException();
        }
        String uid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUid(uid);
        //�����ʼ����û�
        sendEmail.sendE(user.getEmail(),uid);


    }

    //����û�
    public int addUser(User user) throws RegisterExcepton{
        int i = userDao.registerUser(user);
        System.out.println(i);
        if (i!=1){
            throw new RegisterExcepton();
        }
        return i;
    }

    //ʹ��code(���code�Ǵ�ҳ���еõ���)��ѯ���ݿ�õ�user����������ݿⷵ��ֵΪnull���׳��쳣
    // ����uid���ж�state��״̬true���׳��쳣��false�θ��û���״̬Ϊtrue
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
