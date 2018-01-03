package com.lanou3g.web;

import com.lanou3g.dao.UserDao;
import com.lanou3g.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //调用queryAll查询方法，的到一个User的集合然后遍历该集合比较是否登录成功
            String username = request.getParameter("username");
            String password = request.getParameter("password");
    //        List<User> users;
            List<User> users = userDao.queryAll();
            for (int i = 0; i < users.size(); i++) {
    //            users.get(i).equals()
                if(users.get(i).equals(username)&&users.get(i).equals(password)){
                    response.sendRedirect("http://localhost:8080/show.html");
                    System.out.println("1--");
                }else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/TextRegister.html");
                    dispatcher.forward(request,response);
                    System.out.println("2--");
                }
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
