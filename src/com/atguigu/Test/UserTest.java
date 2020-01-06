package com.atguigu.Test;

import com.atguigu.domain.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @auther lizongxiao
 * @date 2020/1/4 - 17:38
 */
public class UserTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void findAllTest(){
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test

    public void addTest(){

    }


}
