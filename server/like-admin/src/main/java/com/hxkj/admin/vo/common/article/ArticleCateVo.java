package com.hxkj.admin.vo.common.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类Vo
 */
@Data
public class ArticleCateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private String name;       // 分类名称
    private Integer sort;      // 排序编号
    private Integer isShow;    // 是否显示
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
