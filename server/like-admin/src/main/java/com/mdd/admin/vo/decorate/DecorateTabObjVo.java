package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DecorateTabObjVo {

    private Integer id;
    private String name;
    private String selected;
    private String unselected;
    private String link;
    private String createTime;
    private String updateTime;

}
