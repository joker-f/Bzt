package com.lanou3g.study;

import com.lanou3g.utils.JdbcUtil;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * ����һ����������
 *
 * ����������������ѡ��ResultSethandler��ʵ����
 *  �����Ϊ���е���:new ScalarHandler<Long>()
 *  �����Ҫһ������ļ���:new BeanListHandler<User>(User.class)
 *  �����Ҫһ������:BeanListler
 *  �����Ҫһ��map:new MapHandler()
 *  �����Ҫһ��map����:new MapListHandler()
 *  �����Ҫһ������:new ArrayHandler()
 *  �����Ҫһ������ļ���:new ArrayListHandler()
 *
 */
public class WhanQueryRunnerTest {
    @Test
    public void queryByParams() throws SQLException {
        Long query = new WhanQueryRunner().query(
                JdbcUtil.getConn(),
                "select count(*) from hw_user",
                new ScalarHandler<Long>());
                //Number��
        // ���Խ����κ���ֵ���͵�ֵ
        // Number�����л�ȡ��������ֵ�ķ���
        Number number=query;//��long���͵�queryת��int���͵�i
        int i = number.intValue();
        System.out.println(i);
/*        for (Object[] objects : query) {
            System.out.println(Arrays.toString(objects));
        }*/

    }

    @Test
    public void queryBean() throws SQLException {
        List<User> query = new WhanQueryRunner().query(
                JdbcUtil.getConn(),
                "select * from hw_user",
                new BeanListHandler<User>(User.class)
        );
        System.out.print(query);
    }


    @Test
    public void queryMap() throws SQLException {
        List<Map<String, Object>> query = new WhanQueryRunner().query(
                JdbcUtil.getConn(),
                "select * from hw_user",
                new MapListHandler()
        );
        System.out.println(query.toString());
    }
}