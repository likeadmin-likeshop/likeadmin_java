package com.hxkj.admin.controller;

import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.plugin.storage.StorageDriver;
import com.hxkj.common.plugin.storage.engine.Aliyun;
import com.hxkj.common.plugin.storage.engine.Qiniu;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @PostMapping("/aa")
    public AjaxResult aa(HttpServletRequest request) {
        MultipartFile multipartFile = ((MultipartRequest) request).getFile("file");
        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传文件");
        }


//        Qiniu qiniu = new Qiniu();
//        qiniu.upload(multipartFile);

        new StorageDriver();

        return AjaxResult.success();
    }

}
