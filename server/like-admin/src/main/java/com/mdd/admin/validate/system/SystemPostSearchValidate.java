package com.mdd.admin.validate.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统岗位搜索参数
 */
@Data
public class SystemPostSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private Integer isStop;

}
