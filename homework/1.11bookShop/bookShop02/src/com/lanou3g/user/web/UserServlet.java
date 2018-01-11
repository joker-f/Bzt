package com.lanou3g.user.web;

import com.lanou3g.cart.domain.Cartltem;
import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.UserService;
import com.lanou3g.user.service.exception.LoginExceptioin;
import com.lanou3g.user.service.exception.RegisterExcepton;
import com.lanou3g.user.service.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet{
    //重写一个登陆注册方法，根据登陆注册提交上来的值，先去父类的方法中利用反射得到一个方法再来调用是哪一个方法
    private UserService userService=new UserService();
    private User userForm=new User();
    private Map<String, String[]> parameterMap ;
    //登录
    // f:表示请求转发
    //r:表示重定向
    // 指定一个路径还得让它的父类BaseServlet能够获得
    public String login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        //获取用户输入的值(从表单提交的值)
        parameterMap= request.getParameterMap();
        String username = request.getParameter("username");
        //将用户信息保存到session域中
        try {
            BeanUtils.populate(userForm,parameterMap);
            System.out.println("-6-"+userForm+"--username");
            User login=userService.login(userForm);
            System.out.println("-1-"+login);
            //将用户信息保存到session域中
            String pUsername = request.getParameter(login.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            //利用cookie
            Cookie loginUsername=new Cookie("users",login.getUsername());
            //购物车
            List<Cartltem> cart=new ArrayList<>();
            session.setAttribute("cart",cart);
            //请求转发
            loginUsername.setPath("/login.jsp");
            //设置在cookie中保存的时间单位是秒
            loginUsername.setMaxAge(60);
            response.addCookie(loginUsername);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (LoginExceptioin loginExceptioin) {
            //失败则抛出异常
            request.setAttribute("error",loginExceptioin.getMessage());
            loginExceptioin.getMessage();
            //请求转发向到登录页面
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);
        }
        return "r:/jsps/main.jsp";
    }
    //注册
    public String register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取用户输入的值，是键值对
        parameterMap = request.getParameterMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            BeanUtils.populate(userForm,parameterMap);
            System.out.println("-206-"+parameterMap+"--"+username+"--"+password+"--"+userForm);
            userService.register(userForm);
            HttpSession session = request.getSession();
            session.setAttribute("users",userForm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }catch (RegisterExcepton registerExcepton) {
            //注册失败则抛出异常
//            request.setAttribute("error",registerExcepton.getMessage());
//            registerExcepton.getMessage();
//            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
        return "f:/jsps/msg.jsp";
    }
    //用户的激活流程
    // 1获取参数：激活码?
    // 2使用激活码来调用action方法，返回得到一个User
    //--如果User为null保存异常信息到request，转发到msg
    //--如果User！null保存激活成功信息到request,转发到msg
    public String active(HttpServletRequest request,HttpServletResponse response) throws RegisterExcepton, ServletException, IOException {
        parameterMap = request.getParameterMap();
        //从表单得到激活码
        String code = request.getParameter("code");
        try {
            BeanUtils.populate(userForm,parameterMap);
            //通过激活码找到user信息
            User active = userService.active(code);
//        String pEmail = request.getParameter("email");
            //将用户存入数据库中
            int i = userService.addUser(userForm);
            System.out.println("-209---"+i);
            HttpSession session = request.getSession();
            session.setAttribute("user",userForm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
        return "f:/jsps/msg.jsp";
    }

    public String quit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "f:/jsps/user/login.jsp";
    }
}
