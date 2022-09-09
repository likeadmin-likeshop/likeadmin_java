package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoticeDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String type;
    private String remarks;
    private Object systemNotice;
    private Object smsNotice;
    private Object oaNotice;
    private Object mnpNotice;

}
