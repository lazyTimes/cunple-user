package com.myapp.test;

import com.myapp.cunpleuserapp.CunpleUserAppApplication;
import com.myapp.cunpleuserapp.service.IUserService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 基准测试springboot
 * @author zhaoxudong
 * @title: JMHSrpingbootTest
 * @projectName cunple
 * @description: 基准测试springboot
 * @date 2019/8/21 14:11
 */
@State(Scope.Thread)
public class JMHSrpingbootTest {

    private ConfigurableApplicationContext applicationContext;
    private IUserService userService;

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder().include(JMHSrpingbootTest.class.getName() + ".*")
                .warmupIterations(2).measurementIterations(2)
                .forks(1).build();

        new Runner(build).run();
    }

    @Setup(Level.Trial)
    public void init(){
        String args = "";
        applicationContext = SpringApplication.run(CunpleUserAppApplication.class, args);
        userService = applicationContext.getBean(IUserService.class);
    }

    /**
     * benchmark执行多次，此注解代表触发我们所要进行基准测试的方法
     */
    @Benchmark
    public void test(){
        System.out.println(userService.getCouponList());
    }
}
