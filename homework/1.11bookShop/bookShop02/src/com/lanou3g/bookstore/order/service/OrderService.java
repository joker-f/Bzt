package com.lanou3g.bookstore.order.service;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.order.dao.OrderDao;
import com.lanou3g.bookstore.order.domain.Order;
import com.lanou3g.bookstore.order.domain.OrderBook;
import com.lanou3g.bookstore.order.domain.OrderItem;
import com.lanou3g.utils.JdbcUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OrderService {
    private OrderDao orderDao=new OrderDao();
    public void add(Order order){
        //1保存订单
        int i = orderDao.addOrder(order);

    }
    //2保存该订单中所有的条目
    public void addOrderItemList(OrderItem orderItems){
            orderDao.addOrderItemList(orderItems);
    }
    //通过uid在orders表中得到订单的详情信息
    public List<Order> findBuUid(String uid){
        List<Order> buUid = orderDao.findBuUid(uid);
        return buUid;
    }
    //通过uid找到oid，--一个集合
    // 再在orderitem表中通过oid找到bid，
    // 再在book表中通过bid找到图书详情，显示到页面上
   /* public List<Book> findByUidOid(String uid){
        List<Book> byOidUid = orderDao.findByUidOid(uid);
//        List byUidBook = orderDao.findByOidUid(byOidUid);
//        List byUidAllBook = orderDao.findByUidAllBook(byUidBook);
        return byOidUid;
    }*/
    public int insertAddress(String address,String oid){
        int i = orderDao.insertAddress(address, oid);
        return i;
    }
    public List<OrderBook> queryBidByoid(){
        List<OrderBook> orderBooks = orderDao.queryBidByoid();
        return orderBooks;
    }

    //生成时间
    public String buttomTime(){
        //获取到点击的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = sdf.format(new Date());
        return format;
    }
    //随机生成编号六位数的编号
    public String runndamId(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace;
    }

}
