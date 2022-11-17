package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 通知设置详情Vo
 */
@Data
public class SettingNoticeDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String type;
    private String remarks;
    private Object systemNotice;
    private Object oaNotice;
    private Object mnpNotice;
    private Object smsNotice;

}
