package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录日志实体
 */
@Data
public class SystemLogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer adminId;
    private String username;
    private String ip;
    private String os;
    private String browser;
    private Integer status;
    private Long createTime;

}
