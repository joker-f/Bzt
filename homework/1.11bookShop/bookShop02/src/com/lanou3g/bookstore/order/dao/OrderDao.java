package com.lanou3g.bookstore.order.dao;

import com.lanou3g.bookstore.book.domain.Book;
import com.lanou3g.bookstore.order.domain.Order;
import com.lanou3g.bookstore.order.domain.OrderBook;
import com.lanou3g.bookstore.order.domain.OrderItem;
import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    QueryRunner queryRunner=new QueryRunner();
    //���涩��
    public int addOrder(Order order){
        String sql="insert into orders value (?,?,?,?,?,?)";
        Connection connection = JdbcUtil.getConnection();
        int i=0;
        try{
            i=queryRunner.update(connection,sql,
                    order.getOid(),
                    order.getOrdertime(),
                    order.getPrice(),
                    order.getState(),
                    order.getUid(),
                    order.getAddress()
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        return i;
    }
    //���涩���е�������Ŀ   ��������������쳣
   /* public void addOrderItemList(List<OrderItem> orderItems){
        JdbcUtil.exectue(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderitem VALUES (?,?,?,?,?)");
            for (OrderItem orderItem : orderItems) {
                String iid = orderItem.getIid();
                String count = orderItem.getCount();
                double subtotal = orderItem.getSubtotal();
                String oid = orderItem.getOid();
                String bid = orderItem.getBid();
                System.out.println("-505---"+iid+","+count+","+subtotal+","+oid+","+bid);
                preparedStatement.addBatch("INSERT  INTO orderitem VALUES (iid,count,subtotal,oid,bid)");
                preparedStatement.executeBatch();
            }
            return preparedStatement;
        });
    }*/

    public int addOrderItemList(OrderItem orderItem){
        String sql="insert into orderitem value (?,?,?,?,?)";
        Connection connection = JdbcUtil.getConnection();
        int update=0;
            try {
                 update = queryRunner.update(connection, sql,
                        orderItem.getIid(),
                        orderItem.getCount(),
                        orderItem.getSubtotal(),
                        orderItem.getOid(),
                        orderItem.getBid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return update;
    }

    public List<Order> findBuUid(String uid){
        //1ʹ��uidΪ������ѯ���еĶ�����һ���û������ж������
        String sql="select * from orders where uid=?";
        Connection connection = JdbcUtil.getConnection();
        List<Order> query =null;
        try {
            query = queryRunner.query(connection, sql, new BeanListHandler<Order>(Order.class), uid);
            //2����ѭ��ÿ��������Ȼ����Ϊÿ�������������Լ���������Ϣ
            /*for (Order order : query) {
                query.add(order);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        //3�������еĶ�����Ϣ
        return query;
    }

    //ͨ��uid�ҵ�oid��--һ������
    // ����orderitem����ͨ��oid�ҵ�bid��
    // ����book����ͨ��bid�ҵ�ͼ�����飬��ʾ��ҳ����
   /* public List<Book> findByUidOid(String uid){
        //1ʹ��uidΪ������ѯ���еĶ�����һ���û������ж������
//        String sql="SELECT * FROM book b INNER JOIN orderitem oi ON b.bid = oi.bid INNER JOIN orders os ON oi.oid = os.oid WHERE oi.oid =?";
        String sql="SELECT b.*,COUNT,o2.oid FROM book b INNER JOIN orderitem o1 on b.bid = o1.bid INNER JOIN orders o2 ON o2.oid = o1.oid WHERE o1.oid =?";
                Connection connection = JdbcUtil.getConnection();
        List<Book> queryOid =null;
        try {
            queryOid = queryRunner.query(connection, sql, new BeanListHandler<Book>(Book.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcUtil.close(connection);
        //3�������еĶ�����Ϣ
        System.out.println("-1111-"+queryOid);
        return queryOid;
    }*/

    public List<OrderBook> queryBidByoid(){
        String sql = "SELECT b.*,COUNT,o2.oid FROM book b INNER JOIN orderitem o1 on b.bid = o1.bid INNER JOIN orders o2 ON o2.oid = o1.oid";
        Connection connection = JdbcUtil.getConnection();
        try {
            List<OrderBook> list = queryRunner.query(connection, sql, new BeanListHandler<OrderBook>(OrderBook.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertAddress(String address,String oid){
        String sql="update orders set address=? where id=?";
        Connection connection = JdbcUtil.getConnection();
        int update=0;
        try {
            update = queryRunner.update(connection, sql, address, oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return update;
    }
}
