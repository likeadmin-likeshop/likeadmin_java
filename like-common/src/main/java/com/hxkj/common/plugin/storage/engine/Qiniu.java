package com.hxkj.common.plugin.storage.engine;

import com.google.gson.Gson;
import com.hxkj.common.exception.OperateException;
import com.hxkj.common.utils.TimeUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Qiniu {

    /**
     * 存储配置
     */
    private final Map<String, String> config;

    /**
     * 构造方法
     */
    public Qiniu(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 鉴权令牌
     *
     * @author fzr
     * @return String
     */
    public String upToken() {
        String accessKey = this.config.get("access_key");
        String secretKey = this.config.get("secret_key");
        String bucket    = this.config.get("bucket");
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }

    /**
     * 上传文件
     *
     * @author fzr
     * @param multipartFile 文件对象
     * @param key 保存文件的名称
     */
    public void upload(MultipartFile multipartFile, String key) {
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Response response = uploadManager.put(multipartFile.getBytes(), key, this.upToken());
            new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (IOException ex) {
            throw new OperateException(ex.getMessage());
        }
    }

}
