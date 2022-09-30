package com.mdd.admin.controller.common;

import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.config.aop.RequestType;
import com.mdd.admin.service.common.IAlbumService;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.AlbumEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.plugin.storage.StorageDriver;
import com.mdd.common.utils.StringUtil;
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
     * @return AjaxResult
     */
    @Log(title = "上传图片", requestType = RequestType.File)
    @PostMapping("/image")
    public AjaxResult image(HttpServletRequest request) {
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            return AjaxResult.failed("请正确选择上传图片");
        }

        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传图片");
        }

        try {
            StorageDriver storageDriver = new StorageDriver();
            Map<String, Object> map = storageDriver.upload(multipartFile, "image", AlbumEnum.IMAGE.getCode());
            String cid = StringUtil.isNotEmpty(request.getParameter("cid")) ? request.getParameter("cid") : "0";

            Map<String, String> album = new LinkedHashMap<>();
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("cid", cid);
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
     * @return AjaxResult
     */
    @Log(title = "上传视频", requestType = RequestType.File)
    @PostMapping("/video")
    public AjaxResult video(HttpServletRequest request) {
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            return AjaxResult.failed("请正确选择上传视频");
        }

        if (multipartFile == null) {
            return AjaxResult.failed("请选择上传视频");
        }

        try {
            StorageDriver storageDriver = new StorageDriver();
            Map<String, Object> map = storageDriver.upload(multipartFile, "video", AlbumEnum.Video.getCode());
            String cid = StringUtil.isNotEmpty(request.getParameter("cid")) ? request.getParameter("cid") : "0";

            Map<String, String> album = new LinkedHashMap<>();
            album.put("cid", cid);
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("type", String.valueOf(AlbumEnum.Video.getCode()));
            album.put("ext", map.get("ext").toString());
            album.put("size", map.get("size").toString());
            album.put("url", map.get("url").toString());
            album.put("name", map.get("name").toString());
            Integer id = iAlbumService.albumAdd(album);

            map.put("id", id);

            return AjaxResult.success(map);
        } catch (OperateException e) {
            return AjaxResult.failed(e.getMsg());
        }
    }

}
