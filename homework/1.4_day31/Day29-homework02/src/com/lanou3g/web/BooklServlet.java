package com.lanou3g.web;

import com.lanou3g.dao.BookDao;
import com.lanou3g.been.Book;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BooklServlet",urlPatterns = "/book")
public class BooklServlet extends HttpServlet {
    private BookDao bookDao=new BookDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       /* List<Book> books;
        books=bookDao.insertAllBook();
        System.out.println("---"+ books);
//        request.setAttribute(books);
        JSONArray jsonArray=JSONArray.fromObject(books);
        response.getWriter().write(jsonArray.toString());*/
    }
}
