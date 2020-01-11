package com.atguigu.service;

import com.atguigu.domain.PageBean;
import com.atguigu.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @auther lizongxiao
 * @date 2020/1/3 - 21:58
 */
public interface UserService {
    /**
     *登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 添加用户信息
     * @param user
     */
    void addUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 删除用户信息
     * @param id
     */
    void deleteUser(String id);

    /**
     * 删除选中
     * @param uids
     */
    void delSelectedUser(String[] uids);

    /**
     * 分页
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
