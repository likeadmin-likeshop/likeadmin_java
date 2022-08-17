package com.hxkj.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类实体
 */
@Data
public class ArticleCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;       // 主键
    private String name;      // 名称
    private Integer sort;     // 排序
    private Integer isShow;   // 是否显示: [0=否, 1=是]
    private Integer isDelete; // 是否删除: [0=否, 1=是]
    private Long createTime;  // 创建时间
    private Long updateTime;  // 更新时间
    private Long deleteTime;  // 删除时间

}
