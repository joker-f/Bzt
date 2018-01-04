package com.lanou3g.web;

import com.lanou3g.dao.UserDao;
import com.lanou3g.been.User;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by zyf on 2017/12/29.
 */
@WebServlet(name = "ShowServlet",urlPatterns = "/show")
public class ShowServlet extends HttpServlet {
	private UserDao userDao = new UserDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 指定允许其他域名访问
		resp.addHeader("Access-Control-Allow-Origin","*");
// 响应类型
		resp.addHeader("Access-Control-Allow-Methods","POST");
// 响应头设置
		resp.addHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
		/*User user=new User();
//		System.out.println("-2-"+user.getUsername());
//		List<User> users;
		//调用queryAll查询方法，的到一个User的集合
		users = userDao.queryByUsername(user.getUsername());
		System.out.println("--"+user1);
		//通过json拉取数据
		JSONArray jsonArray =
				JSONArray.fromObject(user1);
		resp.getWriter().write(jsonArray.toString());*/
	}
}
