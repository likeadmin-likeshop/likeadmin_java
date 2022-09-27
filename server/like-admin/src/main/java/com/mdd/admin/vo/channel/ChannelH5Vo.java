package com.mdd.admin.vo.channel;

import lombok.Data;

/**
 * H5渠道Vo
 */
@Data
public class ChannelH5Vo {

    private Integer status; // 是否关闭
    private Integer close;  // 关闭类型
    private String url;     // 关闭访问

}
