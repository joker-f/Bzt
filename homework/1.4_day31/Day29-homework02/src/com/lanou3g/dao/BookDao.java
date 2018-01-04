package com.lanou3g.dao;

import com.lanou3g.been.Book;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private QueryRunner qr=new QueryRunner();
    //通过书名查询书
    public Book insertBook(String bname){
        String sql = "insert into book where bname=?";
        Connection conn= JdbcUtil.getConnection();
        Book book=new Book();
        try {
             book = qr.query(conn, sql, new BeanHandler<Book>(Book.class), bname);
            System.out.println("--"+book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //插叙全部书籍的，然后通过返回值利用json拉取数据
    public List<Book> insertAllBook(){
        String sql="select * from book";
        Connection conn = null;
        conn = JdbcUtil.getConnection();
        try {
            List<Book> book=qr.query(conn,sql,new BeanListHandler<Book>(Book.class));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        JdbcUtil.close(conn);
    }
        return null;
    }

    //添加书籍,还需要判断是否添加成功
    public void insert(Book book){
        String sql="insert into book values(null,?,?,?)";
        Connection conn=JdbcUtil.getConnection();
        try {
            qr.update(conn,sql,book.getBname(),book.getBauthor(),book.getBprice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
