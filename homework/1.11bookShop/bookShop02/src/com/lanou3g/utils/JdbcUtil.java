package com.lanou3g.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static String url;
//    private static String driverClass;
    private static String user;
    private static String password;
    //��̬����飺������ļ��ض������ڴ�����
    static{
        try {
            //�������������������������д�����ļ�
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
            Properties info=new Properties();
            //�����е����ݣ����ؽ�info��������У�
            // ��ָ����jdbc.properties��������ļ���
            // Ҳ����˵���е����ݾ����ļ��е�����
            info.load(resourceAsStream);
            String driverClass = info.getProperty("driverClass");
            user=info.getProperty("user");
            url=info.getProperty("url");
            password=info.getProperty("password");
            Class.forName(driverClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //������ʱ�׳��쳣
            throw new RuntimeException("driver���·��д����");
        }
    }
    //�������ݿ�ķ���
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("����");
//            System.err.println(e.getMessage());
        }
        return connection;
    }
    //��д
    public static void close(ResultSet resultSet){
        close(resultSet,null,null);
    }
    public static void close(Statement statement){
        close(null,statement,null);
    }
    public static void close(Connection connection){
        close(null,null,connection);
    }
    public static void close(ResultSet resultSet,
                             Statement statement,Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //ʵ���˽ӿڵ�����
    public static void exectue(ExeuteInter e){
        try {
            //���
            // ����
            Connection connection = DriverManager.getConnection(url,user,password);
            e.exevtue(connection).close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
