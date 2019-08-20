package com.myapp.cunpleuserapp.service;

/**
 * 业务层测试
 * @author zhaoxudong
 * @title: MyTestService
 * @projectName cunple
 * @description: 业务层测试
 * @date 2019/8/19 15:18
 */
public interface IUserService {

    /**
     * 测试接口
     */
    void run();

    /**
     * 根据id查找用户
     * @param i 用户id
     * @return
     */
    Object getUserById(int i);
}
