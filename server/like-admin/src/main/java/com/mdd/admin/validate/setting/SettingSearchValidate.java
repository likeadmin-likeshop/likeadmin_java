package com.mdd.admin.validate.setting;

import com.mdd.admin.vo.setting.SettingSearchObjectVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 热门搜索设置参数
 */
@Data
public class SettingSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer isHotSearch;

    private List<SettingSearchObjectVo> list;

}
