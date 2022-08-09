package com.hxkj.admin.vo.common.album;

import lombok.Data;

import java.io.Serializable;

/**
 * 相册Vo
 */
@Data
public class AlbumVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private Integer cid;       // 所属类目
    private String name;       // 文件名称
    private String uri;        // 文件路径
    private String ext;        // 文件扩展
    private String size;       // 文件大小
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
