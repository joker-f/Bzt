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
    //静态代码块：随着类的加载而进入内存运行
    static{
        try {
            //类加载器，可以用它来加载以写配置文件
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc.properties");
            Properties info=new Properties();
            //将流中的内容，加载进info这个对象中，
            // 流指向了jdbc.properties这个配置文件，
            // 也就是说流中的内容就是文件中的内容
            info.load(resourceAsStream);
            String driverClass = info.getProperty("driverClass");
            user=info.getProperty("user");
            url=info.getProperty("url");
            password=info.getProperty("password");
            Class.forName(driverClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //在运行时抛出异常
            throw new RuntimeException("driver类的路径写错了");
        }
    }
    //连接数据库的方法
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("错了");
//            System.err.println(e.getMessage());
        }
        return connection;
    }
    //重写
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
    //实现了接口的引用
    public static void exectue(ExeuteInter e){
        try {
            //获得
            // 连接
            Connection connection = DriverManager.getConnection(url,user,password);
            e.exevtue(connection).close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
