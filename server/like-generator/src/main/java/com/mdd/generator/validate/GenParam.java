package com.mdd.generator.validate;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import com.mdd.common.validator.annotation.StringContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GenParam implements Serializable {

    public interface delete{}

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "ids参数缺失", groups = {delete.class})
    private List<Integer> ids;

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

    @NotNull(message = "authorName参数缺失")
    @NotEmpty(message = "作者名称不能为空")
    @Length(min = 1, max = 100, message = "作者名称不能大于60个字符")
    private String authorName;

    @Length(max = 60, message = "备注不能大于200个字符")
    private String remarks;

    @NotNull(message = "genTpl参数缺失")
    @NotEmpty(message = "请选择生成模板")
    @StringContains(values = {"crud", "tree"}, message = "选择的生成模板不符合")
    private String genTpl;

    @NotNull(message = "moduleName参数缺失")
    @NotEmpty(message = "生成模块名不能为空")
    @Length(min = 1, max = 60, message = "生成模块名不能大于60个字符")
    private String moduleName;

    @NotNull(message = "functionName参数缺失")
    @NotEmpty(message = "生成功能名不能为空")
    @Length(min = 1, max = 60, message = "生成功能名不能大于60个字符")
    private String functionName;

    @NotNull(message = "genType参数缺失")
    @IntegerContains(values = {0, 1}, message = "选择的生成代码方式不符合")
    private Integer genType;

    @Length(max = 200, message = "生成代码路径不能大于200个字符")
    private String genPath = "/";

    private List<Map<String, String>> column = new ArrayList<>();

    private String treePrimary = "";
    private String treeParent  = "";
    private String treeName  = "";

    private String subTableName = "";
    private String subTableFk = "";
    private String subTableFr = "";

    private Integer menuStatus = 2;
    private Integer menuPid = 0;
    private String menuName = "";

}
