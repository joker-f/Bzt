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
        //1���涩��
        int i = orderDao.addOrder(order);

    }
    //2����ö��������е���Ŀ
    public void addOrderItemList(OrderItem orderItems){
            orderDao.addOrderItemList(orderItems);
    }
    //ͨ��uid��orders���еõ�������������Ϣ
    public List<Order> findBuUid(String uid){
        List<Order> buUid = orderDao.findBuUid(uid);
        return buUid;
    }
    //ͨ��uid�ҵ�oid��--һ������
    // ����orderitem����ͨ��oid�ҵ�bid��
    // ����book����ͨ��bid�ҵ�ͼ�����飬��ʾ��ҳ����
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

    //����ʱ��
    public String buttomTime(){
        //��ȡ�������ʱ��
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = sdf.format(new Date());
        return format;
    }
    //������ɱ����λ���ı��
    public String runndamId(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace;
    }

}
