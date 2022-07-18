package com.hxkj.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据Vo
 */
@Data
public class DictDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer typeId;
    private String name;
    private String value;
    private String remark;
    private Integer sort;
    private Integer status;
    private String createTime;
    private String updateTime;

}
