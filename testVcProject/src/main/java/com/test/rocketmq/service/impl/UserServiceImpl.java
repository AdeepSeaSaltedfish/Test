package com.test.rocketmq.service.impl;

import com.test.rocketmq.dao.UserDao;
import com.test.rocketmq.model.User;
import com.test.rocketmq.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    // 注册,向数据库中添加数据
    @Transactional
    public void register(User user){
        userDao.register(user);
    }

    // 登录,查询数据库中的数据
    public User login(User user){
        String username = user.getUsername();     // 获取用户输入的username和password
        String password = user.getPassword();
        if(username == null || password == null){  // 如果有一个为空，就返回null
            return null;
        }
        // 如果用户输入的username和password和List中的所对应，那么就返回该user
        return userDao.login(user);
    }
}
