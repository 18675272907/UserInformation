package com.atguigu.dao;

import com.atguigu.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @auther lizongxiao
 * @date 2020/1/3 - 21:55
 */
public interface UserDao {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

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
    void add(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(int id);

    /**
     * 查询总记录
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     *每页数据
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
