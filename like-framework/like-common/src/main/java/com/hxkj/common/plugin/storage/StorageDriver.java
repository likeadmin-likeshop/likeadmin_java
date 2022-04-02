package com.hxkj.common.plugin.storage;

import com.hxkj.common.config.GlobalConfig;
import com.hxkj.common.exception.OperateException;
import com.hxkj.common.plugin.storage.engine.AliyunStorage;
import com.hxkj.common.plugin.storage.engine.LocalStorage;
import com.hxkj.common.plugin.storage.engine.QcloudStorage;
import com.hxkj.common.plugin.storage.engine.QiniuStorage;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.TimeUtil;
import com.hxkj.common.utils.UrlUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
     * @param folder 文件夹
     * @param type 类型: 10=图片, 20=视频
     */
    public Map<String, Object> upload(MultipartFile multipartFile, String folder, Integer type) {
        this.checkFile(multipartFile, type);
        String key = this.buildSaveName(multipartFile);
        switch (this.engine) {
            case "local":
                LocalStorage local = new LocalStorage();
                local.upload(multipartFile, key, folder);
                break;
            case "qiniu":
                QiniuStorage qiniu = new QiniuStorage(this.config);
                qiniu.upload(multipartFile, folder + "/" + key);
                break;
            case "aliyun":
                AliyunStorage aliyun = new AliyunStorage(this.config);
                aliyun.upload(multipartFile, folder + "/" + key);
                break;
            case "qcloud":
                QcloudStorage qcloud = new QcloudStorage(this.config);
                qcloud.upload(multipartFile, folder + "/" + key);
                break;
        }

        String origFileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        String origFileExt  = origFileName.substring(origFileName.lastIndexOf(".")).replace(".", "");
        String newFileName  = folder + "/" + key;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 0);
        map.put("name", multipartFile.getOriginalFilename());
        map.put("size", multipartFile.getSize());
        map.put("ext", origFileExt.toLowerCase());
        map.put("url", newFileName);
        map.put("path", UrlUtil.toAbsoluteUrl(newFileName));
        return map;
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
        String date = TimeUtil.timestampToDate(TimeUtil.timestamp(), "yyyyMMdd");
        return date + "/" + UUID.randomUUID() + ext.toLowerCase();
    }

    /**
     * 文件验证
     *
     * @author fzr
     * @param multipartFile 文件对象
     * @param type 类型: 10=图片, 20=视频
     */
    private void checkFile(MultipartFile multipartFile, Integer type) {
        String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        String fileExt  = fileName.substring(fileName.lastIndexOf(".")).replace(".", "").toLowerCase();
        long fileSize   = multipartFile.getSize();

        if (type == 10) {
            if (!Arrays.asList(GlobalConfig.uploadImageExt).contains(fileExt)) {
                throw new OperateException("不被支持的扩展:" + fileExt);
            }
            if (fileSize > GlobalConfig.uploadImageSize) {
                throw new OperateException("上传图片不能超出限制:" + GlobalConfig.uploadImageSize);
            }
        } else if (type == 20) {
            if (!Arrays.asList(GlobalConfig.uploadVideoExt).contains(fileExt)) {
                throw new OperateException("不被支持的扩展:" + fileExt);
            }
            if (fileSize > GlobalConfig.uploadVideoSize) {
                throw new OperateException("上传视频不能超出限制:" + GlobalConfig.uploadImageSize);
            }
        }
    }

}
