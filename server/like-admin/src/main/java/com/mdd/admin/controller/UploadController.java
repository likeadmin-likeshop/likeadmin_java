package com.mdd.admin.controller;

import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.aop.Log;
import com.mdd.admin.aop.aspect.RequestType;
import com.mdd.admin.service.IAlbumsService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/upload")
@Api(tags = "上传文件管理")
public class UploadController {

    @Resource
    IAlbumsService iAlbumsService;

    @Log(title = "上传图片", requestType = RequestType.File)
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

        StorageDriver storageDriver = new StorageDriver();
        UploadFilesVo vo = storageDriver.upload(multipartFile, "image", AlbumEnum.IMAGE.getCode());
        String cid = StringUtils.isNotEmpty(request.getParameter("cid")) ? request.getParameter("cid") : "0";

        Map<String, String> album = new LinkedHashMap<>();
        album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
        album.put("cid", cid);
        album.put("type", String.valueOf(AlbumEnum.IMAGE.getCode()));
        album.put("size", vo.getSize().toString());
        album.put("ext", vo.getExt());
        album.put("url", vo.getUrl());
        album.put("name", vo.getName());
        Integer id = iAlbumsService.albumAdd(album);

        vo.setId(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "上传视频", requestType = RequestType.File)
    @PostMapping("/video")
    @ApiOperation(value="上传视频")
    public AjaxResult<UploadFilesVo> video(HttpServletRequest request) {
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            throw new OperateException("请选择上传视频!");
        }

        if (multipartFile == null) {
            throw new OperateException("请选择上传视频!");
        }

        StorageDriver storageDriver = new StorageDriver();
        UploadFilesVo vo = storageDriver.upload(multipartFile, "video", AlbumEnum.Video.getCode());
        String cid = StringUtils.isNotEmpty(request.getParameter("cid")) ? request.getParameter("cid") : "0";

        Map<String, String> album = new LinkedHashMap<>();
        album.put("cid", cid);
        album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
        album.put("type", String.valueOf(AlbumEnum.Video.getCode()));
        album.put("ext", vo.getExt());
        album.put("size", vo.getSize().toString());
        album.put("url", vo.getUrl());
        album.put("name", vo.getName());
        Integer id = iAlbumsService.albumAdd(album);

        vo.setId(id);
        return AjaxResult.success(vo);
    }

}
