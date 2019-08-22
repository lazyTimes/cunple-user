package com.myapp.cunpleuserapp.service.dubbo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxudong
 * @title: SchedulingTest
 * @projectName cunple
 * @description: TODO
 * @date 2019/8/21 17:06
 */
@Service
public class SchedulingTest {

    @Scheduled(cron = "0/10 * * * * ?")
    public void runTest(){
        System.err.println("hello this is scheduling");
    }


}
