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
    //添加 到购物车
    public String add(HttpServletRequest request,HttpServletResponse response){
        //1从session中得到车（登录时已经添加了车）
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //2获取表单参数
        //3通过bid的到book对象（使用BookService )
        String bname = request.getParameter("bname");
        System.out.println("-401-"+bname);
        BookDao bookDao=new BookDao();
        Book book = bookDao.selectBookId(bname);
        String count =request.getParameter("count");
        System.out.println("-402--"+count);
        System.out.println("-404-"+book);
        //4使用Book和数量来创建cartItem
        Cartltem cartltem=new Cartltem(book,count);
        System.out.println("-403-"+cartltem);
        //5把caritem添加到车中
        cart.add(cartltem);
        System.out.println("-405-"+cart);
        session.setAttribute("cart",cart);
        //6返回到/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }
    //清空购物车
    public String clear(HttpServletRequest request,HttpServletResponse response){
        //1从session中得到车
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //2调用车的clear方法
        cart.clear();
        //3返回到/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }

    //删除购物车
    public String delete(HttpServletRequest request,HttpServletResponse response){
        //1得到bid
        String bname = request.getParameter("bname");
        System.out.println("-407--"+bname);
        //2得到车
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //3调用车的delete方法
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBname().equals(bname)){
                System.out.println("-408--"+cart.get(i));
                cart.remove(cart.get(i));
            }
        }
        session.setAttribute("cart",cart);
        System.out.println("-406--"+cart);
        //4返回到/jsps/cart/list.jsp
        return "r:/jsps/cart/list.jsp";
    }
}
