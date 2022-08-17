package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统岗位Vo
 */
@Data
public class SystemAuthPostVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private String code;       // 岗位编号
    private String name;       // 岗位名称
    private String remarks;    // 岗位备注
    private Integer sort;      // 岗位排序
    private Integer isStop;    // 是否停用: [0=否, 1=是]
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
