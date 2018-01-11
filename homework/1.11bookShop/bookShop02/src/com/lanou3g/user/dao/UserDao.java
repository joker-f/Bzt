package com.lanou3g.user.dao;

import com.lanou3g.user.domain.User;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner=new QueryRunner();

    //根据用户名查询用户全部的信息
    public User queryByUsernameAll(String username){
        String sql="select * from user where username=?";
        //调用连接
        Connection connection = JdbcUtil.getConnection();
        User allUser=new User();
        try {
            allUser = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username);
            System.out.println("-5-"+allUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回一个user对象
        return allUser;
    }

    //注册添加用户
    public int registerUser(User user){
        String sql="insert into user value(?,?,?,?,?,?)";
        Connection connection = JdbcUtil.getConnection();
        int i=0;
        //往数据库中添加数据
        try {
            //得到的i如果是1则插入成功，如果是0则插入失败
             i = queryRunner.update(connection, sql,
                     user.getUid(),
                     user.getUsername(),
                     user.getPassword(),
                     user.getEmail(),
                     user.getCode(),
                     user.isState()
             );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        //设置默认返回值为插入不成功
        return i;
    }

    //使用code查询数据库，的到user对象,如果数据库返回null，抛出异常
    public User findByCode(String code){
        String sql="select * from user where code=?";
        Connection connection = JdbcUtil.getConnection();
        User allUser=new User();
        try {
            allUser = queryRunner.query(connection,sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        return allUser;
    }
    //2查看用户状态true抛出异常false：修改用户的状态为true
    public void seletcUid(String uid,boolean state){
        String sql="select * from user where uid=?";
        Connection connection = JdbcUtil.getConnection();
        User allUser=new User();
        try {
            allUser = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), uid);
            if (allUser!=null){
                if (state==false){
                    System.out.println("-2010-"+allUser.getUid());
                    updateState(allUser.getUid());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
    }
    //修改state为true；
    public void updateState(String uid){
        String sql="update user set state=? where uid=?";
        Connection connection = JdbcUtil.getConnection();
//        User allUser=new User();
        try {
            int update = queryRunner.update(connection, sql, true, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
    }
}
