package com.hxkj.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成业务实体
 */
@Data
public class GenTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String tableName;
    private String tableComment;
    private String subTableName;
    private String subTableFk;
    private String entityName;
    private String packageName;
    private String moduleName;
    private String businessName;
    private String functionName;
    private String functionAuthor;
    private String genTpl;
    private String genType;
    private String genPath;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
