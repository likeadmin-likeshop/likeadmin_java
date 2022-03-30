package com.hxkj.common.plugin.storage;

import com.hxkj.common.plugin.storage.engine.Qiniu;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class StorageDriver {

    /**
     * 当前存储引擎
     */
    private final String engine;

    /**
     * 存储引擎配置
     */
    private final Map<String, String> config;

    /**
     * 构造方法
     */
    public StorageDriver() {
        this.engine = ConfigUtil.get("storage", "default", "local");
        this.config = ConfigUtil.getMap("storage", this.engine);
    }

    /**
     * 根据引擎类型上传文件
     *
     * @author fzr
     * @param multipartFile 文件对象
     */
    public void upload(MultipartFile multipartFile) {
        String key = this.buildSaveName(multipartFile);
        switch (this.engine) {
            case "local":
                break;
            case "qiniu":
                Qiniu qiniu = new Qiniu(this.config);
                qiniu.upload(multipartFile, key);
                break;
        }
    }

    /**
     * 生成文件名称
     *
     * @author fzr
     * @param multipartFile 文件对象
     * @return String
     */
    private String buildSaveName(MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();
        String ext  = Objects.requireNonNull(name).substring(name.lastIndexOf("."));
        String date = TimeUtil.timestampToDate(TimeUtil.timestamp(), "Ymd");
        return date + "/" + UUID.randomUUID() + ext.toLowerCase();
    }

}
