package com.mdd.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户授权实体
 */
@Data
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;       // 主键
    private Integer userId;   // 用户Id
    private String openid;    // Openid
    private String unionid;   // Unionid
    private Integer terminal;   // 客户端类型: [1=微信小程序, 2=微信公众号, 3=手机H5；4=电脑PC, 5=苹果APP, 6=安卓APP]
    private Long createTime;  // 创建时间
    private Long updateTime;  // 更新时间

}
