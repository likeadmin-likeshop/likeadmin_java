package com.mdd.admin.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章列表Vo
 */
@Data
public class ArticleListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private String category;   // 分类
    private String title;      // 标题
    private String image;      // 图片
    private Integer visit;     // 访问
    private Integer sort;      // 排序
    private Integer isShow;    // 是否显示: [0=否, 1=是]
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
