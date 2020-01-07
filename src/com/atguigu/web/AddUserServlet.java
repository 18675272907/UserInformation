package com.atguigu.web;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 添加
 * @auther lizongxiao
 * @date 2020/1/4 - 18:01
 */
@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = req.getParameterMap();

        //敏感词汇验证
        for (String[] values : map.values()) {
            for (String value : values) {
                System.out.println(value);
            }
        }


        //3.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用Service保存
        UserService userService = new UserServiceImpl();
        userService.addUser(user);
        //5.跳转到userListServlet
        resp.sendRedirect(req.getContextPath() + "/findUserByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
