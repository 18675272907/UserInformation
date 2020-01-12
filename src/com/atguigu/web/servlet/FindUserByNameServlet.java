package com.atguigu.web.servlet;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证用户名
 * @auther lizongxiao
 * @date 2020/1/11 - 12:10
 */
@WebServlet("/findUserByNameServlet")
public class FindUserByNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);
        //调用service层判断用户名是否存在
        //设置响应的数据格式为json
        resp.setContentType("application/json;charset=utf-8");
        Map<String, Object> map = new HashMap<String,Object>();

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(username);
        System.out.println(username + ":" + user.getName());
        if ((user.getName()).equals(username)){
            //存在
            map.put("userExsit",true);
            map.put("msg","此用户名太受欢迎,请更换一个");
        }else {
            //不存在
            map.put("userExsit",false);
            map.put("msg","用户名可用");
        }

        //将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
