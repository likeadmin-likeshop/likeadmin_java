package com.hxkj.generator.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门Vo
 */
@Data
public class SystemDeptVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pid;
    private String name;
    private String duty;
    private String mobile;
    private Integer sort;
    private Integer isStop;
    private String createTime;
    private String updateTime;

}
