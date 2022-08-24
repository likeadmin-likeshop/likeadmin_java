package com.mdd.common.entity.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 通知设置实体
 */
@Data
public class NoticeSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;          // 主键ID
    private Integer scene;       // 场景编号
    private String name;         // 场景名称
    private String remarks;      // 场景描述
    private Integer recipient;   // 接收人员: [1=用户, 2=平台]
    private Integer type;        // 通知类型: [1=业务, 2=验证码]
    private String systemNotice; // 系统的通知设置
    private String smsNotice;    // 短信的通知设置
    private String oaNotice;     // 公众号通知设置
    private String mnpNotice;    // 小程序通知设置
    private String support;      // 支持的发送类型
    private Integer isDelete;    // 是否删除: [0=否, 1=是]
    private Long createTime;     // 创建时间
    private Long updateTime;     // 更新时间
    private Long deleteTime;     // 删除时间

}
