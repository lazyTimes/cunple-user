package com.myapp.cunpleuserapp.config;

import org.apache.dubbo.config.MetadataReportConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dubbo 数据配置
 * @author zhaoxudong
 * @title: DubboConfig
 * @projectName cunple
 * @description: dubbo 数据配置
 * @date 2019/8/20 19:23
 */
@Configuration
public class DubboConfig {

    /**
     * 元数据配置
     * @return
     */
    @Bean
    public MetadataReportConfig metadataReportConfig() {
        MetadataReportConfig metadataReportConfig = new
                MetadataReportConfig();
        metadataReportConfig.setAddress("zookeeper://119.23.219.183:2181");
        return metadataReportConfig;
    }
}
