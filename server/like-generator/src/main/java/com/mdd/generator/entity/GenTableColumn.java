package com.mdd.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成表列实体类
 */
@Data
public class GenTableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer tableId;
    private String columnName;
    private String columnComment;
    private String columnLength;
    private String columnType;
    private String javaType;
    private String javaField;
    private Integer isPk;
    private Integer isIncrement;
    private Integer isRequired;
    private Integer isInsert;
    private Integer isEdit;
    private Integer isList;
    private Integer isQuery;
    private String queryType;
    private String htmlType;
    private String dictType;
    private Integer sort;
    private Long createTime;
    private Long updateTime;

}
