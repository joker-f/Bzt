package com.lanou3g.user.web;

import com.lanou3g.user.domain.User;
import com.lanou3g.user.service.UserService;
import com.lanou3g.user.service.exception.LoginExceptioin;
import com.lanou3g.user.service.exception.RegisterExcepton;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport implements ServletRequestAware{
    private UserService userService=new UserService();
    private User userForm=new User();
    private Map<String, String[]> parameterMap;
    private HttpServletRequest request;
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }
    public String login() throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        parameterMap = request.getParameterMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        System.out.println("--"+user);
        System.out.println("-1"+userForm);
        try {
            User login = userService.login(user);
            System.out.println("2-"+login);
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            //利用cookie
            Cookie loginUsername=new Cookie("users",login.getUsername());
            //请求转发
//            loginUsername.setPath("/login.jsp");
            //设置在cookie中保存的时间单位是秒
            loginUsername.setMaxAge(60);
//            response.addCookie(loginUsername);
//            return SUCCESS;
        } catch (LoginExceptioin loginExceptioin) {
            loginExceptioin.printStackTrace();
        }
        return "success`";

    }
    public String regist() throws UnsupportedEncodingException {
//        request.setCharacterEncoding("utf-8");
        parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(userForm,parameterMap);
            userService.register(userForm);
            return SUCCESS;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (RegisterExcepton registerExcepton) {
            registerExcepton.getMessage();
        }
        return ERROR;
    }


}
