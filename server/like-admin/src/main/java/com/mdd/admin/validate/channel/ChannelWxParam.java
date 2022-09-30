package com.mdd.admin.validate.channel;

import com.mdd.admin.validate.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 开放平台渠道参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChannelWxParam extends BaseParam {

    @Length(max = 100, message = "appId不能超100个字符")
    private String appId = "";

    @Length(max = 200, message = "appSecret不能超100个字符")
    private String appSecret = "";

}
