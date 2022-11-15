package com.mdd.admin.vo.setting;

import com.mdd.common.entity.setting.HotSearch;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 热门搜索详情Vo
 */
@Data
public class SettingSearchDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer isHotSearch;
    private List<HotSearch> list;

}
