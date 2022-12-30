package com.mdd.generator.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DbColumnVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String columnName;      // 字段名称
    private String columnComment;   // 字段描述
    private String columnType;      // 字段类型

}
