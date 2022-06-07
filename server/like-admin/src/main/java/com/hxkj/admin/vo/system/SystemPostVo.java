package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统岗位Vo
 */
@Data
public class SystemPostVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String code;
    private String name;
    private String remarks;
    private Integer sort;
    private Integer isStop;
    private String createTime;
    private String updateTime;

}
