package com.hxkj.common.plugin.storage.engine;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.ByteArrayInputStream;

public class Aliyun {

    public void upload() {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4G9XZP9MKQ2AmeZJGRTE";
        String accessKeySecret = "qbA9DoJ41jnmpoKhiZqmU1dfHFcpvN";

        String bucketName = "yixiangonline";
        String objectName = "exampleobject.txt";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            String content = "Hello OSS";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
