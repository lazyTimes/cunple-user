package com.myapp.test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 学习使用Google guava 框架
 * @author zhaoxudong
 * @title: GuavaTest
 * @projectName cunple
 * @description: 学习使用Google guava 框架
 * @date 2019/8/21 15:32
 */
public class GuavaTest {

    // 连接器
    private static final Joiner joinner = Joiner.on(",").skipNulls();

    // 分割器
    private static final Splitter spliter = Splitter.on(",").trimResults(CharMatcher.is('_')).omitEmptyStrings();

    /**
     * Joiner是连接器，Splitter是分割器，通常我们会把它们定义为static final，利用on生成对象后在应用到String进行处理，这是可以复用的。
     * 要知道apache commons StringUtils提供的都是static method。更加重要的是，guava提供的Joiner/Splitter是经过充分测试，它的稳定性和效率要比apache高出不少，
     * 这个你可以自行测试下~发现没有我们想对String做什么操作，就是生成自己定制化的Joiner/Splitter，多么直白，简单，流畅的API！对于Joiner，
     * 常用的方法是 跳过NULL元素：skipNulls() / 对于NULL元素使用其他替代：useForNull(String)对于Splitter，
     * 常用的方法是： trimResults()/omitEmptyStrings()。注意拆分的方式，有字符串，还有正则，还有固定长度分割（太贴心了！）

     * @param args
     */
    public static void main(String[] args) {
        String join = joinner.join(Lists.newArrayList("a", null, "b"));
        System.err.println("join = " + join);

        for (String tmp : spliter.split("____a,     __, __b ,, s__da")) {
            System.err.println("|" + tmp + "|");
        }
    }
}
