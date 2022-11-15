package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * H5渠道Vo
 */
@Data
public class ChannelH5Vo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status; // 是否关闭
    private Integer close;  // 关闭类型
    private String url;     // 关闭访问

}
