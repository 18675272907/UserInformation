package com.atguigu.web;

import com.atguigu.domain.PageBean;
import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 分页
 * @auther lizongxiao
 * @date 2020/1/5 - 21:59
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String currentPage = req.getParameter("currentPage");//当前页码
        String rows = req.getParameter("rows");//每页显示条数

        if (currentPage == null || "".equals(currentPage) ){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "5";
        }

        Map<String, String[]> condition = req.getParameterMap();

        //敏感词汇验证
        for (String[] values : condition.values()) {
            for (String value : values) {
                System.out.println(value);
            }
        }

        UserService userService = new UserServiceImpl();
        PageBean<User> pb = userService.findUserByPage(currentPage,rows,condition);
        System.out.println(pb);

        req.setAttribute("pb",pb);
        req.setAttribute("condition",condition);

        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
