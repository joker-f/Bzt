package com.lanou3g.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {
    //�õ�һ������Դ�����ӳأ�
    private static DataSource dataSource=new ComboPooledDataSource();
    //дһ�����ӷ���
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            //����ʱ�׳��쳣
            throw new RuntimeException("������æ....");
        }
    }
    //�ر�����
    public static void release(ResultSet resultSet, Statement statement,Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet=null;
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement=null;
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection=null;
        }
    }
}
