package com.fanzhou.service;

import com.fanzhou.entity.User;

/**
 * @author fanzhou
 * @create 2020/8/22 0022
 */
public interface UserService {
    void save(User user);

    User findUserByName(String username);

    User findUserByUser(User user);
}
