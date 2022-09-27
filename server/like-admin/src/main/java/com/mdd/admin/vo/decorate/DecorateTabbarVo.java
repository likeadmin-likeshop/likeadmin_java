package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DecorateTabbarVo {

    private Map<String, String> style;
    private List<DecorateTabObjVo> list;

}
