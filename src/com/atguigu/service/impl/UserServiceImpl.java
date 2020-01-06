package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.domain.PageBean;
import com.atguigu.domain.User;
import com.atguigu.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @auther lizongxiao
 * @date 2020/1/3 - 21:58
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 添加用户信息
     * @param user
     */
    @Override
    public void addUser(User user) {
        userDao.add(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    /**
     * 删除用户信息
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        userDao.delete(Integer.parseInt(id));
    }

    /**
     * 删除选中
     * @param uids
     */
    @Override
    public void delSelectedUser(String[] uids) {
        if (uids != null && uids.length > 0){
            for (String uid : uids) {
                userDao.delete(Integer.parseInt(uid));
            }
        }
    }

    /**
     * 分页
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);//当前页码
        int rows = Integer.parseInt(_rows);//每页显示条数

        if (currentPage <= 0){
            currentPage = 1;
        }

        PageBean<User> pb = new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //查询总记录数
        int totalCount = userDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //查询每页数据List集合
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start,rows,condition);
        pb.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }


}
