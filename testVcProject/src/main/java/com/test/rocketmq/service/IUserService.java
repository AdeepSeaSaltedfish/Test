package com.test.rocketmq.service;

import com.test.rocketmq.model.User;


public interface IUserService {


    // 注册,向数据库中添加数据
    public void register(User user);

    //; 登录,查询数据库中的数据
    public User login(User user);
}
