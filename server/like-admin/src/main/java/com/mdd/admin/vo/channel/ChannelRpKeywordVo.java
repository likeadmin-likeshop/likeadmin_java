package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 关键词回复Vo
 */
@Data
public class ChannelRpKeywordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String keyword;
    private String content;
    private Integer contentType;
    private Integer matchingType;
    private Integer sort;
    private Integer status;

}
