package com.lanou3g.bookstore.book.dao;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {
    private QueryRunner queryRunner=new QueryRunner();

    //在类别表中按照类别找到书的类型，然后再在书表中找到类型
    public List<Book> queryByCategoryAll(String cname){
//        String sql="select * from book where cid=（select cid from category where cname=?）";
        String sql="select * from book where cid=?";
        Connection connection = JdbcUtil.getConnection();
        List<Book> categoryBook=null;
        try {
             categoryBook=queryRunner.query(connection,sql,new BeanListHandler<Book>(Book.class),cname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        return categoryBook;
    }

    //显示全部的图书
    public List<Book> selectAllBook(){
        String sql="select * from book";
        Connection connection = JdbcUtil.getConnection();
        List<Book> allBook = null;
        try {
            allBook = queryRunner.query(connection, sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        return allBook;
    }

    public Book selectBookId(String bname){
        String sql = "select * from book where bname=?";
        Connection conn= JdbcUtil.getConnection();
        Book book=new Book();
        try {
            book = queryRunner.query(conn, sql, new BeanHandler<Book>(Book.class), bname);
//            System.out.println("--"+book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(conn);
        return book;
    }
}
