package com.mdd.admin.validate.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统部门搜索参数
 */
@Data
public class SystemDeptSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer isStop;

}
