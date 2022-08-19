package com.mdd.generator.vo;

import lombok.Data;

import java.io.Serializable;

/***
 * 列实体
 */
@Data
public class GenColumnVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;             // 字段主键
    private String columnName;      // 字段名称
    private String columnComment;   // 字段描述
    private Integer columnLength;   // 字段长度
    private String columnType;      // 字段类型
    private String javaType;        // JAVA类型
    private String javaField;       // JAVA字段
    private Integer isRequired;     // 是否必填
    private Integer isInsert;       // 是否插入字段
    private Integer isEdit;         // 是否编辑字段
    private Integer isList;         // 是否列表字段
    private Integer isQuery;        // 是否查询字段
    private String queryType;       // 查询方式: [等于、不等于、大于、小于、范围]
    private String htmlType;        // 显示类型: [文本框、文本域、下拉框、复选框、单选框、日期控件]
    private String dictType;        // 字典类型
    private String createTime;      // 创建时间
    private String updateTime;      // 更新时间

}
