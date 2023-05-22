package com.test.rocketmq.dao;

import com.test.rocketmq.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    // 注册用户
    public void register(User user);


    // 登录用户
    public User login(User user);
}
