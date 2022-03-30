package com.hxkj.common.plugin.storage.engine;

import com.hxkj.common.exception.OperateException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class Local {

    @Resource
    HttpServletRequest request;

    public void upload() {
        MultipartFile multipartFile = ((MultipartRequest) request).getFile("file");
        if (multipartFile == null) {
            throw new OperateException("请选择要上传的图片");
        }

        // 文件信息
        String origFileName = multipartFile.getOriginalFilename();
        String origFileExt  = Objects.requireNonNull(origFileName).substring(origFileName.lastIndexOf("."));
        long origFileSize   = multipartFile.getSize();
        String newsFileName = UUID.randomUUID() + origFileExt;
        String newsSavePath = "";

        // 创建目录
        File fileExist = new File(newsSavePath);
        if (!fileExist.exists()) {
            if (!fileExist.mkdirs()) {
                throw new OperateException("创建上传目录失败");
            }
        }

        // 保存文件
        try {
            File dest = new File(newsSavePath, newsFileName);
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            throw new OperateException("上传文件失败:"+e.getMessage());
        }

    }

}
