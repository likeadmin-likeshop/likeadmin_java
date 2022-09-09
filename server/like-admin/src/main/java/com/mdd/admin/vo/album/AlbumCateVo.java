package com.mdd.admin.vo.album;

import lombok.Data;

import java.io.Serializable;

/**
 * 相册分类Vo
 */
@Data
public class AlbumCateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private Integer pid;       // 类目父级
    private String name;       // 类目名称
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
