package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 公众号默认回复Vo
 */
@Data
public class ChannelRpDefaultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String content;
    private Integer contentType;
    private Integer sort;
    private Integer status;

}
