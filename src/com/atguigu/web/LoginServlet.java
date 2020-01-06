package com.atguigu.web;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 登录
 * @auther lizongxiao
 * @date 2020/1/4 - 22:19
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");
        //获取用户填写验证码
        String verifycode = req.getParameter("verifycode");
        //验证码校验
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            //验证码不正确
            //提示信息
            req.setAttribute("login_msg","验证码错误!");
            //跳转登录页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

        Map<String, String[]> map = req.getParameterMap();
        //封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        //判断是否登录成功
        if (loginUser != null) {
            //登录成功
            session.setAttribute("user",loginUser);
            //跳转页面
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }else {
            //登录失败
            req.setAttribute("login_msg","用户名或密码错误!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
