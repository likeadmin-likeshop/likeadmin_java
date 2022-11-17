package com.mdd.admin.validate.channel;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公众号默认回复
 */
@Data
public class ChannelRpDefaultValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "name参数缺失")
    @NotEmpty(message = "规则名称不能为空")
    private String name;

    @NotNull(message = "content参数缺失")
    @NotEmpty(message = "回复内容不能为空")
    private String content;

    @NotNull(message = "contentType参数缺失")
    @IntegerContains(values = {1, 2}, message = "contentType值不符合规范")
    private Integer contentType;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {0, 1}, message = "状态选择异常")
    private Integer status;

    @NotNull(message = "sort参数缺失")
    private Integer sort;

}
