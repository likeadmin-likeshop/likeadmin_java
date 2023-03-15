package com.mdd.admin.validate.channel;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("公众号关注回复参数")
public class ChannelRpFollowsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "name参数缺失")
    @NotEmpty(message = "规则名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @NotNull(message = "content参数缺失")
    @NotEmpty(message = "回复内容不能为空")
    @ApiModelProperty(value = "回复内容", required = true)
    private String content;

    @NotNull(message = "contentType参数缺失")
    @IntegerContains(values = {1, 2}, message = "contentType值不符合规范")
    @ApiModelProperty(value = "内容类型", required = true)
    private Integer contentType;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {0, 1}, message = "状态选择异常")
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    @NotNull(message = "sort参数缺失")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;

}
