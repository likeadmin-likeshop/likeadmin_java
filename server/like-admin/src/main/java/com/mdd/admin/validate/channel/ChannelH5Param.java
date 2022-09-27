package com.mdd.admin.validate.channel;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * H5渠道参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChannelH5Param extends BaseParam {

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {0, 1}, message = "status不是合法值")
    private Integer status;

    @NotNull(message = "close参数确实")
    @IntegerContains(values = {0, 1}, message = "close不是合法值")
    private Integer close;

    @Length(max = 500, message = "url不能超500个字符")
    private String url;

}
