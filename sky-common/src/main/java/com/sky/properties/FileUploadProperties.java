package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.upload")
@Data
public class FileUploadProperties {

    private String localPath; // 本地文件存储路径
    private String urlPrefix; // 文件访问URL前缀

}
