package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统岗位实体
 */
@Data
public class SystemAuthDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer pid;
    private String name;
    private String duty;
    private String mobile;
    private Integer sort;
    private Integer isStop;
    private Integer isDelete;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
