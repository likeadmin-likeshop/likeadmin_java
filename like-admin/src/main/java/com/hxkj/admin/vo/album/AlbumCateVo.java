package com.hxkj.admin.vo.album;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumCateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pid;
    private String name;
    private String createTime;
    private String updateTime;

}
