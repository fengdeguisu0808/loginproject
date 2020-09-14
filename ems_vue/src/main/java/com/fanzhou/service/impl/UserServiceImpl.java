package com.fanzhou.service.impl;

import com.fanzhou.dao.UserDao;
import com.fanzhou.entity.User;
import com.fanzhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author fanzhou
 * @create 2020/8/22 0022
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        user.setStatus("已激活");
        user.setRegisterTime(new Date());
        userDao.save(user);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName( username);
    }

    @Override
    public User findUserByUser(User user) {
        return userDao.findUserByUser( user);
    }
}
