package com.sky.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Data
@AllArgsConstructor
@Slf4j
public class LocalFileUtil {

    private String localPath;
    private String urlPrefix;

    /**
     * 文件上传到本地
     *
     * @param bytes            文件字节数组
     * @param originalFilename 原始文件名
     * @return 文件访问URL
     */
    public String upload(byte[] bytes, String originalFilename) {
        // 获取文件扩展名
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + extension;

        // 确保存储目录存在
        File directory = new File(localPath);
        if (!directory.exists()) {
            directory.mkdirs();
            log.info("创建文件存储目录: {}", localPath);
        }

        // 保存文件
        File file = new File(localPath + File.separator + fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
            log.info("文件上传成功: {}", file.getAbsolutePath());
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }

        // 返回文件访问路径
        String fileUrl = urlPrefix + "/" + fileName;
        log.info("文件访问URL: {}", fileUrl);
        return fileUrl;
    }
}
