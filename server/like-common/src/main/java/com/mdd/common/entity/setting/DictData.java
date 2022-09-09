package com.mdd.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据实体
 */
@Data
public class DictData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;       // 主键
    private Integer typeId;   // 类型
    private String name;      // 键名
    private String value;     // 数值
    private String remark;    // 备注
    private Integer sort;     // 排序
    private Integer status;   // 状态: [0=停用, 1-正常]
    private Integer isDelete; // 是否删除: [0=否, 1=是]
    private Long createTime;  // 创建时间
    private Long updateTime;  // 更新时间
    private Long deleteTime;  // 删除时间

}
