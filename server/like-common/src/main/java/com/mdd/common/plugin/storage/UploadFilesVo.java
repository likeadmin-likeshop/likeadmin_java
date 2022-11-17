package com.mdd.common.plugin.storage;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadFilesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Long size;
    private String ext;
    private String url;
    private String path;

}
