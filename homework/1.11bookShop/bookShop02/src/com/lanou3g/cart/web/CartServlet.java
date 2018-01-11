package com.lanou3g.cart.web;

import com.lanou3g.bookstore.book.dao.BookDao;
import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.cart.domain.Cartltem;
import com.lanou3g.user.service.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "CartServlet",urlPatterns = "/cart")
public class CartServlet extends BaseServlet{
    //��� �����ﳵ
    public String add(HttpServletRequest request,HttpServletResponse response){
        //1��session�еõ�������¼ʱ�Ѿ�����˳���
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //2��ȡ������
        //3ͨ��bid�ĵ�book����ʹ��BookService )
        String bname = request.getParameter("bname");
        System.out.println("-401-"+bname);
        BookDao bookDao=new BookDao();
        Book book = bookDao.selectBookId(bname);
        String count =request.getParameter("count");
        System.out.println("-402--"+count);
        System.out.println("-404-"+book);
        //4ʹ��Book������������cartItem
        Cartltem cartltem=new Cartltem(book,count);
        System.out.println("-403-"+cartltem);
        //5��caritem��ӵ�����
        cart.add(cartltem);
        System.out.println("-405-"+cart);
        session.setAttribute("cart",cart);
        //6���ص�/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }
    //��չ��ﳵ
    public String clear(HttpServletRequest request,HttpServletResponse response){
        //1��session�еõ���
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //2���ó���clear����
        cart.clear();
        //3���ص�/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }

    //ɾ�����ﳵ
    public String delete(HttpServletRequest request,HttpServletResponse response){
        //1�õ�bid
        String bname = request.getParameter("bname");
        System.out.println("-407--"+bname);
        //2�õ���
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //3���ó���delete����
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBname().equals(bname)){
                System.out.println("-408--"+cart.get(i));
                cart.remove(cart.get(i));
            }
        }
        session.setAttribute("cart",cart);
        System.out.println("-406--"+cart);
        //4���ص�/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }
}
