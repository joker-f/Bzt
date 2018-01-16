package com.lanou3g.user.dao;


import com.lanou3g.user.domain.User;
import com.lanou3g.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner=new QueryRunner();

    //�����û�����ѯ�û�ȫ������Ϣ
    public User queryByUsernameAll(String username){
        String sql="select * from user where username=?";
        Connection connection = C3P0Util.getConnection();
        User allUser=new User();
        try {
            allUser = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        C3P0Util.release(null,null,connection);
        //����һ��user����
        return allUser;
    }

    //ע������û�
    public int registerUser(User user){
        String sql="insert into user value(?,?,?,?,?,?)";
        Connection connection = C3P0Util.getConnection();
        int i=0;
        try {
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
        C3P0Util.release(null,null,connection);
        return i;
    }

    //ʹ��code��ѯ���ݿ⣬�ĵ�user����,������ݿⷵ��null���׳��쳣
    public User findByCode(String code){
        String sql="select * from user where code=?";
        Connection connection = C3P0Util.getConnection();
        User allUser=new User();
        try {
            allUser = queryRunner.query(connection,sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        C3P0Util.release(null,null,connection);
        return allUser;
    }
    //2�鿴�û�״̬true�׳��쳣false���޸��û���״̬Ϊtrue
    public void seletcUid(String uid,boolean state){
        String sql="select * from user where uid=?";
        Connection connection = C3P0Util.getConnection();
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
        C3P0Util.release(null,null,connection);
    }
    //�޸�stateΪtrue��
    public void updateState(String uid){
        String sql="update user set state=? where uid=?";
        Connection connection = C3P0Util.getConnection();
//        User allUser=new User();
        try {
            int update = queryRunner.update(connection, sql, true, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        C3P0Util.release(null,null,connection);
    }
}
