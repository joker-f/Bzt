package com.lanou3g.homework01.JdbcUtil;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class lianjie {

    public static void Jdbc() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userwork",
                "root",
                "123");
        System.out.println(connection.getClass().getName());
        String sql="select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        JdbcUtil.showRS(resultSet);
        resultSet.close();
        statement.close();
        connection.close();
    }
    //×¢²á
    public void add(String username, String password,String age, String gender){
        JdbcUtil.exectue(conn -> {
            PreparedStatement pstate = conn.prepareStatement(
                    "INSERT INTO user VALUES (NULL ,"+username+","+password+","+age+","+gender+")");
            pstate.execute();
            return pstate;

        });
    }

    //µÇÂ¼
    public ResultSet select() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javasehomework"
                , "root"
                , "123"
        );
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.close();
        statement.close();
        connection.close();

        return resultSet;
    }
}
