package com.hxkj.common.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统操作日志实体
 */
@Data
public class LogOperate implements Serializable
{
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
    private Long startTime;
    private Long endTime;
    private Long taskTime;
    private Long createTime;

}
