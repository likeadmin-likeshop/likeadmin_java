package com.mdd.common.entity.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 通知记录实体
 */
@Data
public class NoticeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer scene;
    private Integer userId;
    private String account;
    private String title;
    private String code;
    private String content;
    private String error;
    private Integer sender;
    private Integer receiver;
    private Integer status;
    private Integer isRead;
    private Integer isCaptcha;
    private Integer isDelete;
    private Long expireTime;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
