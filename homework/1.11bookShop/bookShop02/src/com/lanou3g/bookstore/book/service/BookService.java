package com.lanou3g.bookstore.book.service;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.book.dao.BookDao;
import com.lanou3g.bookstore.book.service.exception.BookException;
import com.lanou3g.bookstore.book.service.exception.NotBookException;

import java.util.List;

public class BookService {
    private BookDao bookDao=new BookDao();

    //∑÷¿‡≤È—Ø
    public List<Book> categoryBook(String cname) throws BookException{
        List<Book> books = bookDao.queryByCategoryAll(cname);
        if (books==null){
            throw new NotBookException();
        }
        return books;
    }
    public List<Book> allBook(){
        List<Book> books = bookDao.selectAllBook();
        return books;
    }

    public Book selectBookId(String id){
        Book bookId = bookDao.selectBookId(id);
        return bookId;
    }
}
