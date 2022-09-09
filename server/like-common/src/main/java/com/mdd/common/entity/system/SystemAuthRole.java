package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色实体
 */
@Data
public class SystemAuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        // 主键
    private String name;       // 角色名称
    private String remark;     // 备注信息
    private Integer sort;      // 角色排序
    private Integer isDisable; // 是否禁用: [0=否, 1=是]
    private Long createTime;   // 创建时间
    private Long updateTime;   // 更新时间

}
