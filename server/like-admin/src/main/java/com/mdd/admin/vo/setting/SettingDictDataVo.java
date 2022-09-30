package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据Vo
 */
@Data
public class SettingDictDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;        // 主键
    private Integer typeId;    // 类型
    private String name;       // 键
    private String value;      // 值
    private String remark;     // 备注
    private Integer sort;      // 排序
    private Integer status;    // 状态: [0=停用, 1=禁用]
    private String createTime; // 创建时间
    private String updateTime; // 更新时间

}
