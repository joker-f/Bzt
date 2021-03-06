package com.lanou3g.dao;

import com.lanou3g.been.User;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zyf on 2017/12/29.
 */
public class UserDao {
	private QueryRunner qr = new QueryRunner();

	public int insertUser(User user) {
		String sql = "insert into user values(null,?,?)";
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
            int i = qr.update(conn, sql, user.getUsername(), user.getPassword());
//            System.out.println(i);
            return i;
        } catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(conn);
        return 0;
	}
	//查询数据库中的所有内容
	public List<User> queryAll() {
		String sql = "select * from user";
		Connection conn = null;
		conn = JdbcUtil.getConnection();
		try {
			List<User> users =
					qr.query(conn, sql, new BeanListHandler<User>(User.class));
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

	//按用户名来查找用户信息
	public User queryByUsername(String username){
		String sql="select * from user where username=?";
		Connection conn=JdbcUtil.getConnection();
		User user=new User();
		try {
			user=qr.query(conn,sql,new BeanHandler<User>(User.class),username);
			System.out.println("---"+user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


}
