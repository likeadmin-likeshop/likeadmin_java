package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 热门搜索对象Vo
 */
@Data
public class SettingSearchObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer sort;

}
