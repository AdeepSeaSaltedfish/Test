package com.test.rocketmq.controller;

import com.test.rocketmq.model.User;
import com.test.rocketmq.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/doLogin")
    public String doLogin(User user){
        userService.login(user);
        return "doLogin.jsp";
    }

    @PostMapping("/doRegister")
    public String doRegister(User user){

        return "doRegister.jsp";
    }

}
