package com.myapp.cunpleuserapp.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * Bean 销毁的时候执行的bean
 * @author zhaoxudong
 * @title: DisposiableBean
 * @projectName cunple
 * @description: Bean 销毁的时候执行的bean
 * @date 2019/8/21 9:31
 */
@Component
public class DisposiableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.err.println("关闭的时候进行的操作");
    }
}
