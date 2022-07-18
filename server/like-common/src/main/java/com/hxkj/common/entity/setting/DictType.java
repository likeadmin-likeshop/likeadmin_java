package com.hxkj.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典类型实体
 */
@Data
public class DictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;         // 字典主键
    private String dictName;    // 字典名称
    private String dictType;    // 字典类型
    private String dictRemark;  // 字典备注
    private Integer dictStatus; // 字典状态: 0=停用, 1=正常
    private Integer isDelete;   // 是否删除: 0=否, 1=是
    private Long createTime;    // 创建时间
    private Long updateTime;    // 更新时间
    private Long deleteTime;    // 删除时间

}
