package com.myapp.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * JMH基准测试代码
 * @author zhaoxudong
 * @title: JMHHelloworld
 * @projectName cunple
 * @description: JMH基准测试代码
 * @date 2019/8/21 11:10
 */
public class JMHHelloworld {

    /**
     * 预热两秒
     * 1. 轮询两次
     * 2. 根据每一次的结果获取执行效率
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(1)
                .build();
        new Runner(options).run();
    }

    /**
     * 测试stringBuffer 使用
     */
    @Benchmark
    public void stringBufferRun(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            stringBuilder.append(i);
        }
        stringBuilder.toString();
    }

    /**
     * 测试stringBuiler 使用
     */
    @Benchmark
    public void stringBuilderRun(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10000000; i++) {
            stringBuffer.append(i);
        }
        stringBuffer.toString();
    }
}
