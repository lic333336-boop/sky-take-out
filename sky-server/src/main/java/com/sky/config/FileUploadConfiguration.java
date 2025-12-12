package com.sky.config;

import com.sky.properties.FileUploadProperties;
import com.sky.utils.LocalFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置类
 */
@Configuration
@Slf4j
public class FileUploadConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LocalFileUtil localFileUtil(FileUploadProperties fileUploadProperties) {
        log.info("开始创建本地文件上传工具类对象：{}", fileUploadProperties);
        return new LocalFileUtil(
                fileUploadProperties.getLocalPath(),
                fileUploadProperties.getUrlPrefix());
    }
}
