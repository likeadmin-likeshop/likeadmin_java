package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 公众号回复Vo
 */
@Data
public class ChannelRpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String keyword;
    private String content;
    private Integer replyType;
    private Integer contentType;
    private Integer matchingType;
    private Integer sort;
    private Integer status;

}
