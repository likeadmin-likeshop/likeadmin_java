package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统操作日志实体
 */
@Data
public class SystemLogOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer adminId;
    private String type;
    private String title;
    private String method;
    private String ip;
    private String url;
    private String args;
    private String error;
    private String address;
    private Integer status;
    private Long startTime;
    private Long endTime;
    private Long taskTime;
    private Long createTime;

}
