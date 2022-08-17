package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置实体
 */
@Data
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;      // 主键
    private String type;     // 类型
    private String name;     // 键
    private String value;    // 值
    private Long createTime; // 创建时间
    private Long updateTime; // 更新时间

}
