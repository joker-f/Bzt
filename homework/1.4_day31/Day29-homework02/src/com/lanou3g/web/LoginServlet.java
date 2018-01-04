package com.lanou3g.web;

import com.lanou3g.dao.UserDao;
import com.lanou3g.been.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao=new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
     /*  String username = request.getParameter("username");
        String password = request.getParameter("password");
         //���session
        HttpSession session=request.getSession();
        session.setAttribute("username",username);
        request.setAttribute("psaaword",password);
        request.getRequestDispatcher("/show.html").forward(request,response);*/

        Map<String, String[]> paramenterMap=request.getParameterMap();
        User user=new User();

        try {
            BeanUtils.populate(user,paramenterMap);
            System.out.println("-1-"+user.getUsername());
            User u=userDao.queryByUsername(user.getUsername());
            System.out.println("-2-"+u);
            if (user.getUsername().equals(u.getUsername())){
                if (user.getPassword().equals(u.getPassword())){
                    //���û���Ϣ���浽session����
                    String username = request.getParameter(u.getUsername());
                    String password = request.getParameter(u.getPassword());
                    //���session
                    HttpSession session=request.getSession();
                    session.setAttribute("user",user);
//                    request.setAttribute("psaaword",password);

                    //�ɹ����浽�Ķ�������indexʱ���Ի�õ�������Servletext������
//                    getServletContext().setAttribute("user",user);
                    Cookie loginUsername=new Cookie("users",u.getUsername());
                    loginUsername.setPath("/login.jsp");
                    loginUsername.setMaxAge(60*60);
                    response.addCookie(loginUsername);

                    request.getRequestDispatcher("/index.jsp").forward(request,response);
                    System.out.println("cg");
//                    response.getWriter().write("��¼�ɹ�");
                    return;
                }else{
                    System.out.println("�û����������");
                }
            }else {
                System.out.println("û���û���");
            }
            System.out.println("3");
            response.sendRedirect("/login.jsp");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("4");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
