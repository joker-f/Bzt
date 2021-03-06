package com.lanou3g.web;

import com.lanou3g.dao.UserDao;
import com.lanou3g.been.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by zyf on 2017/12/29.
 */
@WebServlet(name = "InsertServlet",urlPatterns = "/insert")
public class InsertServlet extends HttpServlet {
	private UserDao userDao = new UserDao();


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
//		String username = req.getParameter("username");
//		String gender = req.getParameter("gender");
//		User user = new User(username,gender);
		//username=zhangsan
		//gender=nan
		Map<String, String[]> parameterMap =
				req.getParameterMap();
		User user = new User();
		try {
			//可以直接把map中的内容设置到对象上
			BeanUtils.populate(user,parameterMap);
			String formName=user.getUsername();
			User fromDb=userDao.queryByUsername(formName);
			if (fromDb!=null){
				resp.sendRedirect("/day27/login.jsp");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

        int insertUser = userDao.insertUser(user);
        if (insertUser==1){
            resp.getWriter().write("SUCCESS");
                resp.setStatus(302);
                resp.sendRedirect("http://localhost:8080/login.jsp");
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/insert");
            dispatcher.include(req,resp);
        }


	}
}
