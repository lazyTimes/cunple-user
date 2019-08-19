package com.myapp.cunpleapp.service.impl;

import com.myapp.cunpleapp.service.MyTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务层测试
 * @author zhaoxudong
 * @title: MyTestService
 * @projectName cunple
 * @description: 业务层测试
 * @date 2019/8/19 15:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyTestServiceImpl implements MyTestService {


    @Override
    public void run() {
        System.err.println("my run is test Service");
    }
}
