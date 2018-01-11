package com.lanou3g.user.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据这个请求参数的（从form表单里串进来的）值来确定到底要执行那个方法，（获取请求参数method）
        String methodName = req.getParameter("method");
        //请求方式有post get put
        //获得当前的类型
        Class<? extends BaseServlet> servlet = this.getClass();
        //servlet的类型是class这是一个类对象，
        // 通过对象的getMethod方法可以获得该类中的方法
        Method method=null;
        try {
           method = servlet.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("-3-"+method);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("这不是一个方法的名字"+methodName);
        }

        try {
            //方法调用方法用invoke
            String result = (String) method.invoke(this, req, resp);
            System.out.println("-4-"+result);
            String[] split = result.split(":");
            String type=split[0];
            String path=split[1];
            if (type.equals("f")){
                //说明请求转发登录页面
                System.out.println("-7-");
                req.getRequestDispatcher(path).forward(req,resp);
//                req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
            }
            if (type.equals("r")){
                //重定向到主页面
                resp.sendRedirect(req.getContextPath()+path);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
