package com.hxkj.generator.validate;

import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.IntegerContains;
import com.hxkj.common.validator.annotation.StringContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 生成参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GenParam implements Serializable {

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "tableName参数缺失")
    @NotEmpty(message = "表名称不能为空")
    @Length(min = 1, max = 200, message = "名称不能大于200个字符")
    private String tableName;

    @NotNull(message = "entityName参数缺失")
    @NotEmpty(message = "实体类名称不能为空")
    @Length(min = 1, max = 200, message = "实体类名称不能大于200个字符")
    private String entityName;

    @NotNull(message = "tableComment参数缺失")
    @NotEmpty(message = "表描述不能为空")
    @Length(min = 1, max = 200, message = "表描述不能大于200个字符")
    private String tableComment;

    @NotNull(message = "functionAuthor参数缺失")
    @NotEmpty(message = "表描述不能为空")
    @Length(min = 1, max = 60, message = "表描述不能大于200个字符")
    private String functionAuthor;

    @Length(max = 60, message = "备注不能大于200个字符")
    private String remarks;

    @NotNull(message = "genTpl参数缺失")
    @NotEmpty(message = "请选择生成模板")
    @StringContains(values = {"curd", "tree"}, message = "选择的生成模板不符合")
    private String genTpl;

    @NotNull(message = "packageName参数缺失")
    @NotEmpty(message = "生成包路径不能为空")
    @Length(min = 1, max = 60, message = "生成包路径不能大于200个字符")
    private String packageName;

    @NotNull(message = "moduleName参数缺失")
    @NotEmpty(message = "生成模块名不能为空")
    @Length(min = 1, max = 60, message = "生成模块名不能大于60个字符")
    private String moduleName;

    @NotNull(message = "businessName参数缺失")
    @NotEmpty(message = "生成业务名不能为空")
    @Length(min = 1, max = 60, message = "生成业务名不能大于60个字符")
    private String businessName;

    @NotNull(message = "functionName参数缺失")
    @NotEmpty(message = "生成功能名不能为空")
    @Length(min = 1, max = 60, message = "生成功能名不能大于60个字符")
    private String functionName;

    @NotNull(message = "genType参数缺失")
    @IntegerContains(values = {0, 1}, message = "选择的生成代码方式不符合")
    private Integer genType;

    @Length(max = 200, message = "生成代码路径不能大于200个字符")
    private String genPath;

}
