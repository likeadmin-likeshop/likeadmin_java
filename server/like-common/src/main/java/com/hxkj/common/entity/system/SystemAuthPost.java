package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统岗位管理
 */
@Data
public class SystemAuthPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private String code;
    private String name;
    private String remarks;
    private Integer sort;
    private Integer isStop;
    private Integer isDelete;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
