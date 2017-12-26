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
        //ע������
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
            System.out.println(e.getMessage()+"\n\t\t"+"�뽫�����ļ�jdbc.properties���õ�srcĿ¼��");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        try {
            //�������
            Connection connection = DriverManager.getConnection(url+database,user,password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //�������
    //������**
    //�ر�����
/*    public static void getStop() throws SQLException {
            Statement statement = jdbcClose.fileClose();
            statement.close();
    }*/
    //ʵ���˽ӿڵ�����
    public static void exectue(ExeuteInter e){
        try {
            //�������
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
                    System.out.println("��¼�ɹ�");
                }else {
                    System.out.println("��¼ʧ��");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
