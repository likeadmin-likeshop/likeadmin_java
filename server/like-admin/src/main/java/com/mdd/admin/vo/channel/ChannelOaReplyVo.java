package com.mdd.admin.vo.channel;

import lombok.Data;

@Data
public class ChannelOaReplyVo {

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
