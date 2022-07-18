package com.hxkj.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典类型Vo
 */
@Data
public class DictTypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String dictName;
    private String dictType;
    private String dictRemark;
    private Integer dictStatus;
    private String createTime;
    private String updateTime;

}
