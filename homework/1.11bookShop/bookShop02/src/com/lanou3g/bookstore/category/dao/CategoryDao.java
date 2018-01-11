package com.lanou3g.bookstore.category.dao;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.category.domain.Category;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner queryRunner=new QueryRunner();
    public List<Category> selectAllCategory(){
        String sql="select * from category";
        Connection connection = JdbcUtil.getConnection();
        List<Category> bookList=null;
        try {
            bookList= queryRunner.query(connection, sql, new BeanListHandler<Category>(Category.class));
            System.out.println("-304-"+bookList);
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(connection);
        }
        JdbcUtil.close(connection);
        return bookList;
    }
}
