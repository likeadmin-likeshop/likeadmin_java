package com.mdd.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门Vo
 */
@Data
public class SystemAuthDeptVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private Integer pid;       // 部门父级
    private String name;       // 部门名称
    private String duty;       // 负责人
    private String mobile;     // 联系电话
    private Integer sort;      // 排序编号
    private Integer isStop;    // 是否停用: [0=否, 1=是]
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
