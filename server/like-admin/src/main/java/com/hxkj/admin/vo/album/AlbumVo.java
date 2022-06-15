package com.hxkj.admin.vo.album;

import lombok.Data;

import java.io.Serializable;

/**
 * 相册Vo
 */
@Data
public class AlbumVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer cid;
    private String name;
    private String uri;
    private String ext;
    private String size;
    private String createTime;
    private String updateTime;

}
