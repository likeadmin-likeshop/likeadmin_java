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
    private Integer id;        // 主键
    private String code;       // 岗位编码
    private String name;       // 岗位名称
    private String remarks;    // 岗位备注
    private Integer sort;      // 岗位排序
    private Integer isStop;    // 是否停用: [0=否, 1=是]
    private Integer isDelete;  // 是否删除: [0=否, 1=是]
    private Long createTime;   // 创建时间
    private Long updateTime;   // 更新时间
    private Long deleteTime;   // 删除时间

}
