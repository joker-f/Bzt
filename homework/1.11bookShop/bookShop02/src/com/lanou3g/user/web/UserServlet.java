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
    //��дһ����½ע�᷽�������ݵ�½ע���ύ������ֵ����ȥ����ķ��������÷���õ�һ������������������һ������
    private UserService userService=new UserService();
    private User userForm=new User();
    private Map<String, String[]> parameterMap ;
    //��¼
    // f:��ʾ����ת��
    //r:��ʾ�ض���
    // ָ��һ��·�����������ĸ���BaseServlet�ܹ����
    public String login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        //��ȡ�û������ֵ(�ӱ��ύ��ֵ)
        parameterMap= request.getParameterMap();
        String username = request.getParameter("username");
        //���û���Ϣ���浽session����
        try {
            BeanUtils.populate(userForm,parameterMap);
            System.out.println("-6-"+userForm+"--username");
            User login=userService.login(userForm);
            System.out.println("-1-"+login);
            //���û���Ϣ���浽session����
            String pUsername = request.getParameter(login.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            //����cookie
            Cookie loginUsername=new Cookie("users",login.getUsername());
            //���ﳵ
            List<Cartltem> cart=new ArrayList<>();
            session.setAttribute("cart",cart);
            //����ת��
            loginUsername.setPath("/login.jsp");
            //������cookie�б����ʱ�䵥λ����
            loginUsername.setMaxAge(60);
            response.addCookie(loginUsername);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (LoginExceptioin loginExceptioin) {
            //ʧ�����׳��쳣
            request.setAttribute("error",loginExceptioin.getMessage());
            loginExceptioin.getMessage();
            //����ת���򵽵�¼ҳ��
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);
        }
        return "r:/jsps/main.jsp";
    }
    //ע��
    public String register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //��ȡ�û������ֵ���Ǽ�ֵ��
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
            //ע��ʧ�����׳��쳣
//            request.setAttribute("error",registerExcepton.getMessage());
//            registerExcepton.getMessage();
//            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
        return "f:/jsps/msg.jsp";
    }
    //�û��ļ�������
    // 1��ȡ������������?
    // 2ʹ�ü�����������action���������صõ�һ��User
    //--���UserΪnull�����쳣��Ϣ��request��ת����msg
    //--���User��null���漤��ɹ���Ϣ��request,ת����msg
    public String active(HttpServletRequest request,HttpServletResponse response) throws RegisterExcepton, ServletException, IOException {
        parameterMap = request.getParameterMap();
        //�ӱ��õ�������
        String code = request.getParameter("code");
        try {
            BeanUtils.populate(userForm,parameterMap);
            //ͨ���������ҵ�user��Ϣ
            User active = userService.active(code);
//        String pEmail = request.getParameter("email");
            //���û��������ݿ���
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
