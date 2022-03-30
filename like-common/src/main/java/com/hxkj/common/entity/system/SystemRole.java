package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色实体
 */
@Data
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String name;
    private String remark;
    private Integer sort;
    private Integer isDisable;
    private Long createTime;
    private Long updateTime;

}
