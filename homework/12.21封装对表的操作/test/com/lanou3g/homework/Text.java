package com.lanou3g.homework;

import com.lanou3g.study.User;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 对userwork中的user表进行增加数据封装
 */
public class Text {
    private QueryRunner runner = null;

    public Text() {
        runner = new QueryRunner();
    }
    //增
    public void add(User user) throws SQLException {
        String sql = "insert into hw_user(uname,age,loc) values(?,?,?)";
        runner.update(
                JdbcUtil.getConn()
                ,sql
                ,user.getUname()
                ,user.getAge()
                ,user.getLoc()
        );
    }
    //删  通过名字来删除
    public void delete(String uname) throws SQLException{
        String sql = "delete from hw_user where uname = ? ";
        runner.update(
                JdbcUtil.getConn()
                ,sql
                ,uname
        );
    }

    //查询一个对象 通过id进行查询
    public User queryById(String id) throws SQLException {
        String sql = "select * from hw_user where uid=?";
        User user = runner.query(
                JdbcUtil.getConn()
                , sql
                , new BeanHandler<User>(User.class)
                , id
        );
        return user;
    }
    //查询结果得到一个集合 查询整个表的内容
    public List<User> queryall() throws SQLException {
        String sql = "select * from hw_user";
        List<User> users = runner.query(
                JdbcUtil.getConn()
                , sql
                , new BeanListHandler<User>(User.class)
        );
        return users;
    }
}
