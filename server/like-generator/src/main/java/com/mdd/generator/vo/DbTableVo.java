package com.mdd.generator.vo;

import lombok.Data;

import java.io.Serializable;

/***
 * 表实体
 */
@Data
public class DbTableVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;     // 表的名称
    private String tableComment;  // 表的描述
    private String authorName;    // 作者名称
    private String createTime;    // 创建时间
    private String updateTime;    // 更新时间

}
