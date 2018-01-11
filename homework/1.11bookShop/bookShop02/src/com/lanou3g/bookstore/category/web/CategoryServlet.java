package com.lanou3g.bookstore.category.web;

import com.lanou3g.bookstore.category.dao.CategoryDao;
import com.lanou3g.bookstore.category.domain.Category;
import com.lanou3g.user.service.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet{
    private Category category=new Category();
    private CategoryDao categoryDao=new CategoryDao();
    public String findAll(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        //1调用service得到结果，保存到request中
//        String cid = request.getParameter("cid");
        List<Category> categories = categoryDao.selectAllCategory();
        System.out.println("-303"+categories);
        HttpSession session = request.getSession();
        session.setAttribute("category",categories);
        //2转发到list.jsp
//        request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
        return "r:/jsps/left.jsp";
    }
}
