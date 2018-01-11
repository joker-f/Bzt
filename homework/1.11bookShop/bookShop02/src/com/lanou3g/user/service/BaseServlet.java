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
        //���������������ģ���form���ﴮ�����ģ�ֵ��ȷ������Ҫִ���Ǹ�����������ȡ�������method��
        String methodName = req.getParameter("method");
        //����ʽ��post get put
        //��õ�ǰ������
        Class<? extends BaseServlet> servlet = this.getClass();
        //servlet��������class����һ�������
        // ͨ�������getMethod�������Ի�ø����еķ���
        Method method=null;
        try {
           method = servlet.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("-3-"+method);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("�ⲻ��һ������������"+methodName);
        }

        try {
            //�������÷�����invoke
            String result = (String) method.invoke(this, req, resp);
            System.out.println("-4-"+result);
            String[] split = result.split(":");
            String type=split[0];
            String path=split[1];
            if (type.equals("f")){
                //˵������ת����¼ҳ��
                System.out.println("-7-");
                req.getRequestDispatcher(path).forward(req,resp);
//                req.getRequestDispatcher("/jsps/msg.jsp").forward(req,resp);
            }
            if (type.equals("r")){
                //�ض�����ҳ��
                resp.sendRedirect(req.getContextPath()+path);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
