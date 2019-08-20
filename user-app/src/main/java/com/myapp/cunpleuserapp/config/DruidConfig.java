package com.myapp.cunpleuserapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * @author zhaoxudong
 * @title: DruidConfig
 * @projectName cunple
 * @description: 数据源配置
 * @date 2019/8/19 11:22
 */
@Configuration
public class DruidConfig {


    @Value("${druid.login.user_name}")
    private String userName;

    @Value("${druid.login.password}")
    private String password;



    /**
     * 数据源的配置
     * @return
     */
    @Bean(name = "default_datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * 配置外部支持
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", userName);// 用户名
        initParameters.put("loginPassword", password);// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }


    /**
     * 过滤其他访问形式
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
