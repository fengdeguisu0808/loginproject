package com.fanzhou.dao;

import com.fanzhou.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fanzhou
 * @create 2020/8/22 0022
 */
@Mapper
public interface UserDao {
    void save(User user);

    User findUserByName(String username);

    User findUserByUser(User user);
}
