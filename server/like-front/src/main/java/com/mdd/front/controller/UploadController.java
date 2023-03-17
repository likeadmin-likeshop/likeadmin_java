package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.AlbumEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.plugin.storage.StorageDriver;
import com.mdd.common.plugin.storage.UploadFilesVo;
import com.mdd.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/upload")
@Api(tags = "上传管理")
public class UploadController {

    @NotLogin
    @PostMapping("/image")
    @ApiOperation(value="上传图片")
    public AjaxResult<UploadFilesVo> image(HttpServletRequest request) {
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            throw new OperateException("请正确选择上传图片!");
        }

        if (multipartFile == null) {
            throw new OperateException("请选择上传图片!");
        }

        String folder = "image";
        if (StringUtils.isNotEmpty(request.getParameter("dir"))) {
            folder += "/" + request.getParameter("dir");
        }

        StorageDriver storageDriver = new StorageDriver();
        UploadFilesVo vo = storageDriver.upload(multipartFile, folder, AlbumEnum.IMAGE.getCode());
        return AjaxResult.success(vo);
    }

}
