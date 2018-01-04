package com.lanou3g.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuitServlet",urlPatterns = "/quit")
public class QuitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.removeAttribute("username");
        //点击退出后重定向到登录页面（怎样在input中显示上次登录的用户名）
        response.setStatus(302);
        response.sendRedirect("/login.jsp");
    }
}
