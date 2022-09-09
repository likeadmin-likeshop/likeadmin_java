package com.mdd.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章实体类
 */
@Data
public class ArticleCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        // 主键
    private Integer userId;    // 用户ID
    private Integer articleId; // 文章ID
    private Integer isDelete;  // 是否删除
    private Long createTime;   // 创建时间
    private Long updateTime;   // 更新时间
    private Long deleteTime;   // 删除时间

}
