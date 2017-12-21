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
 * 创建一个测试用例
 *
 * 根据需求结果的类型选择ResultSethandler的实现类
 *  结果即为单行单列:new ScalarHandler<Long>()
 *  如果相要一个对象的集合:new BeanListHandler<User>(User.class)
 *  如果想要一个对象:BeanListler
 *  如果想要一个map:new MapHandler()
 *  如果想要一个map集合:new MapListHandler()
 *  如果想要一个数组:new ArrayHandler()
 *  如果想要一个数组的集合:new ArrayListHandler()
 *
 */
public class WhanQueryRunnerTest {
    @Test
    public void queryByParams() throws SQLException {
        Long query = new WhanQueryRunner().query(
                JdbcUtil.getConn(),
                "select count(*) from hw_user",
                new ScalarHandler<Long>());
                //Number类
        // 可以接收任何数值类型的值
        // Number类中有获取各种整型值的方法
        Number number=query;//把long类型的query转换int类型的i
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