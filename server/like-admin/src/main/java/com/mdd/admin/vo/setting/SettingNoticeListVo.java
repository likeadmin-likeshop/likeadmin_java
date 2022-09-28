package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 通知设置列表Vo
 */
@Data
public class SettingNoticeListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;           // 主键
    private String name;          // 通知名称
    private String type;          // 通知类型
    private Integer systemStatus; // 通知状态
    private Integer smsStatus;    // 通知状态
    private Integer oaStatus;     // 公众号状态
    private Integer mnpStatus;    // 小程序状态
    private String createTime;    // 创建时间
    private String updateTime;    // 更新时间

}
