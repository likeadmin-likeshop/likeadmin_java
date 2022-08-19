package com.mdd.common.plugin.storage.engine;

import com.mdd.common.exception.OperateException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * 腾讯云存储
 */
public class QcloudStorage {

    /**
     * 存储配置
     */
    private final Map<String, String> config;

    /**
     * 构造方法
     */
    public QcloudStorage(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 鉴权客户端
     *
     * @author fzr
     * @return String
     */
    public COSClient cosClient() {
        String secretId     = this.config.get("accessKey");
        String secretKey    = this.config.get("secretKey");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.config.get("region"));
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param key 文件名称 20220331/11.png
     */
    public void upload(MultipartFile multipartFile, String key) {
        try {
            byte[] data = multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(data);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(this.config.get("bucket"), key, inputStream, objectMetadata);
            this.cosClient().putObject(putObjectRequest);
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new OperateException(e.getMessage());
        }
    }



}
