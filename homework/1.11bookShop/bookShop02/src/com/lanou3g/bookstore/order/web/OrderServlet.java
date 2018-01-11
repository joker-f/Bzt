package com.lanou3g.bookstore.order.web;

import com.lanou3g.bookstore.book.dao.BookDao;
import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.order.domain.Order;
import com.lanou3g.bookstore.order.domain.OrderBook;
import com.lanou3g.bookstore.order.domain.OrderItem;
import com.lanou3g.bookstore.order.service.OrderService;
import com.lanou3g.cart.domain.Cartltem;
import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    private Order order = new Order();
    private OrderItem orderItem = new OrderItem();
    private OrderService orderService = new OrderService();
    private String count;
    private String Bid;
    private String buttomTime;
    private String runndamOId;
    private double money;

    //生成订单
    public String add(HttpServletRequest request, HttpServletResponse response) {
        //从session中获取到cart
        HttpSession session = request.getSession();
        List<Cartltem> cart = (List<Cartltem>) session.getAttribute("cart");
        //2使用cart来生成order(将cater中的信息存入到order中)
        User user = (User) request.getSession().getAttribute("user");
        for (Cartltem cartltem : cart) {
             count = cartltem.getCount();
             Bid=cartltem.getBook().getBid();
        }
        System.out.println("-501-"+user);
        money = (double) session.getAttribute("money");
        System.out.println("-502-" + money);
        buttomTime = orderService.buttomTime();
        runndamOId = orderService.runndamId();
        session.setAttribute("runndamOId", runndamOId);
        session.setAttribute("buttomTime", buttomTime);

        order.setOid(runndamOId);
        order.setOrdertime(buttomTime);
        order.setUid(user.getUid());
        order.setState(false);
        order.setPrice(money);
        session.setAttribute("order",order);
        orderService.add(order);
        //根据uid找到用户中所有的订单
       /* List<Order> buUid = orderService.findBuUid(user.getUid());
        System.out.println("-503-"+buUid);
        session.setAttribute("buUid",buUid);*/
        //通过uid找到用户订单中所有书籍的信息
        /*List<Book> byUidOid = orderService.findByUidOid("0ffcb25072b34fbab70d1140471b4377");
        for (Book book : byUidOid) {
            System.out.println("-123---"+book.getBname());
        }
        System.out.println("-505-"+user.getUid());
        System.out.println("--504--"+byUidOid);
        session.setAttribute("byUidOid",byUidOid);*/
        //将数据存入orderitem中间表中
        //将结算的用户信息提交到orderitem表中
//        List<OrderItem> orderItemList=new ArrayList<>();
        for (int i = 0; i < cart.size(); i++) {
            orderItem.setIid(orderService.runndamId());
            orderItem.setCount(count);
            orderItem.setOid(runndamOId);
            System.out.println("--504--"+count);
            orderItem.setSubtotal(money);
            orderItem.setBid(Bid);
//            orderItemList.add(orderItem);
            orderService.addOrderItemList(orderItem);
    }
        //清空数据库放在最后支付成功后再清楚
        cart.clear();
        //从数据orders表中通过用户id找到所有的商品的详情，再存到session中，再再页面中获得

        //通过通过uid知道所有的书
        return "f:/jsps/order/desc.jsp";
    }

    public String myOrders(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        //得到uid
        User user = (User) request.getSession().getAttribute("user");
        List<Order> buUid = orderService.findBuUid(user.getUid());
        request.setAttribute("buUid",buUid);
        List<OrderBook> orderBooks = orderService.queryBidByoid();
        request.setAttribute("orderBooks",orderBooks);
        //清空数据库放在最后支付成功后再清楚
        return "f:/jsps/order/list.jsp";
    }
    //添加地址
    public String addAddress(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        String address = (String) session.getAttribute("address");
        int i = orderService.insertAddress(address, runndamOId);
        return "f:/jsps/order/desc.jsp";
    }
    public String load(){

        return "f:/jsps/order/desc.jsp";
    }

    public String confirm(){

        return "f:/jsps/msg.jsp";
    }
}
