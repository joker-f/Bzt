package com.lanou3g.homework01.JdbcUtil;

import com.lanou3g.study.ExeuteInter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static String url;
    private static String database;
    private static String user;
    private static String password;
    static {
        //注册驱动
        try {
            Properties properties=new Properties();
            properties.load(new FileReader("src/jdbc.properties"));
            String driverName = properties.getProperty("driverName");
            url = properties.getProperty("url");
            database = properties.getProperty("database");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()+"\n\t\t"+"请将配置文件jdbc.properties放置到src目录下");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        try {
            //获得连接
            Connection connection = DriverManager.getConnection(url+database,user,password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获得连接
    //做事情**
    //关闭连接
/*    public static void getStop() throws SQLException {
            Statement statement = jdbcClose.fileClose();
            statement.close();
    }*/
    //实现了接口的引用
    public static void exectue(ExeuteInter e){
        try {
            //获得连接
            Connection connection = DriverManager.getConnection(url+database,user,password);
            e.exevtue(connection).close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void showRS(ResultSet resultSet){
        try {
            while (resultSet.next()){
                System.out.print(resultSet.getString(1)+"\t");
                System.out.print(resultSet.getString(2)+"\t");
                System.out.print(resultSet.getString(3)+"\t");
                System.out.print(resultSet.getString(4)+"\t");
                System.out.println();
                if (resultSet.getString(2).equals("zss")&&resultSet.getString(3).equals("1234")){
                    System.out.println("登录成功");
                }else {
                    System.out.println("登录失败");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
