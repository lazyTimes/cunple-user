package com.myapp.cunpleuserapp.controller;

import com.myapp.cunpleuserapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author zhaoxudong
 * @title: UserController
 * @projectName cunple
 * @description: 用户控制器
 * @date 2019/8/20 11:21
 */
@RestController
public class UserController {


    @Autowired
    private IUserService userService;

    @RequestMapping("/user")
    public Object test(){
        return userService.getUserById(1);
    }


    @RequestMapping("/list")
    public Object list(){

        return userService.getCouponList();
    }
}
