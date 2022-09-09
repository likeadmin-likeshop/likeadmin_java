package com.mdd.generator.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 生成列表Vo
 */
@Data
public class GenTableVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;           // 生成主键
    private Integer genType;      // 生成类型: [0=zip压缩包, 1=自定义路径]
    private String tableName;     // 表的名称
    private String tableComment;  // 表的描述
    private String createTime;    // 创建时间
    private String updateTime;    // 删除时间

}
