package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统短信日志实体
 */
@Data
public class SystemLogSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;      // 主键
    private String scene;    // 场景编号
    private String mobile;   // 手机号码
    private String content;  // 发送内容
    private Integer status;  // 发送状态：[0=发送中, 1=发送成功, 2=发送失败]
    private String results;  // 发送结果
    private Long sendTime;   // 发送时间
    private Long createTime; // 创建时间
    private Long updateTime; // 更新时间

}
