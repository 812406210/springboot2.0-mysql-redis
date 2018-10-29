package com.cn.commodity.service;

import com.cn.commodity.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User getUserById(int userId);

    /**
     * 添加用户
     * @param record
     * @return
     */
    boolean addUser(User record);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser();

}
