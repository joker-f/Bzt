package com.lanou3g.bookstore.book.web;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.book.service.BookService;
import com.lanou3g.bookstore.book.service.exception.BookException;
import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.BaseServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet{
    private BookService bookService=new BookService();
    private Map<String,String[]> parameterMap;
    private List<User> list;
    //��ѯ����ͼ��
    public String findAll(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        //1����service�õ���������浽request��
//        String cid = request.getParameter("cid");
        List<Book> books = bookService.allBook();
        System.out.println("-301"+books);
        HttpSession session = request.getSession();
        session.setAttribute("books",books);
        //2ת����list.jsp
//        request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
        return "r:/jsps/book/list.jsp";
    }
    //�������ѯ
    public String findByCategory(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, BookException {
        request.setCharacterEncoding("utf-8");
        //1����service�ĵ���������浽request��
        String cid = request.getParameter("cid");
        System.out.println("-302-"+cid);
        List<Book> books = bookService.categoryBook(cid);
        HttpSession session = request.getSession();
        session.setAttribute("books",books);
        //2ת����list.jsp
//        request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
        return "r:/jsps/book/list.jsp";
    }

    //��ѯͼ��������Ϣ
    // 2ʹ��service�õ�book����
    //3ת����/jsps/book/desc.jsp
    public String load(HttpServletRequest request,HttpServletResponse response){
        //1��ȡbid
        String bid = request.getParameter("bid");
        System.out.println("-303-"+bid);
        Book book = bookService.selectBookId(bid);
        System.out.println("-304-"+book);
        request.setAttribute("book",book);
        return "f:/jsps/book/desc.jsp";
    }
}
