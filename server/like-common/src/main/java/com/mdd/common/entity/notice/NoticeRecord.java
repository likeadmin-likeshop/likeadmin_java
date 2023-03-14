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
    private Integer id;         // 主键
    private Integer scene;      // 场景
    private Integer userId;     // 用户
    private String account;     // 账号
    private String title;       // 标题
    private String code;        // 编码
    private String content;     // 内容
    private String error;       // 错误
    private Integer sender;     // 发送类型: [1=系统, 2=短信, 3=公众号, 4=小程序]
    private Integer receiver;   // 接收对象: [1=用户, 2=平台]
    private Integer status;     // 通知状态: [0=等待, 1=成功, 2=失败]
    private Integer isRead;     // 已读状态: [0=未读, 1=已读]
    private Integer isCaptcha;  // 是验证码: [0=否的, 1=是的]
    private Integer isDelete;   // 是否删除: [0=否的, 1=是的]
    private Long expireTime;    // 失效时间
    private Long createTime;    // 创建时间
    private Long updateTime;    // 更新时间
    private Long deleteTime;    // 删除时间

}
