package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.AlbumEnum;
import com.mdd.common.plugin.storage.StorageDriver;
import com.mdd.common.utils.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 上传管理
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    /**
     * 上传图片
     *
     * @author fzr
     * @param request 请求对象
     * @return Object
     */
    @PostMapping("/image")
    public Object image(HttpServletRequest request) {

        System.out.println(request.getParameter("dir"));
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            return AjaxResult.failed("请正确选择上传图片");
        }

        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传图片");
        }

        String folder = "image";
        if (StringUtil.isNotEmpty(request.getParameter("dir"))) {
            folder += "/" + request.getParameter("dir");
        }

        StorageDriver storageDriver = new StorageDriver();
        Map<String, Object> map = storageDriver.upload(multipartFile, folder, AlbumEnum.IMAGE.getCode());
        return AjaxResult.success(map);
    }

}
