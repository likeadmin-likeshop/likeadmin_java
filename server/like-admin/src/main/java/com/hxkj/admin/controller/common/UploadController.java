package com.hxkj.admin.controller.common;

import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.common.IAlbumService;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.enums.AlbumEnum;
import com.hxkj.common.exception.OperateException;
import com.hxkj.common.plugin.storage.StorageDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 上传管理
 */
@RestController
@RequestMapping("api/common/upload")
public class UploadController {

    @Resource
    IAlbumService iAlbumService;

    /**
     * 上传图片
     *
     * @author fzr
     * @param request 请求对象
     * @return Object
     */
    @Log(title = "上传图片")
    @PostMapping("/image")
    public Object image(HttpServletRequest request) {
        MultipartFile multipartFile = ((MultipartRequest) request).getFile("file");
        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传图片");
        }

        try {
            StorageDriver storageDriver = new StorageDriver();
            Map<String, Object> map = storageDriver.upload(multipartFile, "image", AlbumEnum.IMAGE.getCode());

            Map<String, String> album = new LinkedHashMap<>();
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("cid", request.getParameter("cid"));
            album.put("type", String.valueOf(AlbumEnum.IMAGE.getCode()));
            album.put("size", map.get("size").toString());
            album.put("ext", map.get("ext").toString());
            album.put("url", map.get("url").toString());
            album.put("name", map.get("name").toString());
            Integer id = iAlbumService.albumAdd(album);

            map.put("id", id);

            return AjaxResult.success(map);
        } catch (OperateException e) {
            return AjaxResult.failed(e.getMsg());
        }
    }

    /**
     * 上传视频
     *
     * @author fzr
     * @param request 请求对象
     * @return Object
     */
    @Log(title = "上传视频")
    @PostMapping("/video")
    public Object video(HttpServletRequest request) {
        MultipartFile multipartFile = ((MultipartRequest) request).getFile("file");
        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传视频");
        }

        try {
            StorageDriver storageDriver = new StorageDriver();
            Map<String, Object> map = storageDriver.upload(multipartFile, "video", AlbumEnum.Video.getCode());

            Map<String, String> album = new LinkedHashMap<>();
            album.put("cid", request.getParameter("cid"));
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("type", String.valueOf(AlbumEnum.Video.getCode()));
            album.put("ext", map.get("ext").toString());
            album.put("size", map.get("size").toString());
            album.put("url", map.get("url").toString());
            Integer id = iAlbumService.albumAdd(album);

            map.put("id", id);

            return AjaxResult.success(map);
        } catch (OperateException e) {
            return AjaxResult.failed(e.getMsg());
        }
    }

}
