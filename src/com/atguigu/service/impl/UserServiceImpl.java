package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.domain.PageBean;
import com.atguigu.domain.Province;
import com.atguigu.domain.User;
import com.atguigu.jedis.util.JedisPoolUtils;
import com.atguigu.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

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
     * 校验用户名是否存在
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
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

    /**
     * 查询所有城市信息
     * @return
     */
    @Override
    public List<Province> findAllProvince() {
        return userDao.findAllProvince();
    }

    /**
     * 缓存
     * @return
     */
    @Override
    public String findAllJson() {
        //1.先从redis中查询数据
        //1.1获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        //2判断 province_json 数据是否为null
        if (province_json == null || province_json.length() == 0){
            //redis中没有数据
            System.out.println("redis中没数据，查询数据库...");
            //2.1从数据中查询
            List<Province> ps = userDao.findAllProvince();
            //2.2将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(ps);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3 将json数据存入redis
            jedis.set("province",province_json);
            //归还连接
            jedis.close();
        }else {
            System.out.println("redis中有数据，查询缓存...");
        }
        return province_json;
    }


}
