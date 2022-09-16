package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 公众号回复实体
 */
@Data
public class OfficialReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;           // 主键ID
    private String name;          // 规则名
    private String keyword;       // 关键词
    private Integer replyType;    // 回复类型: [1=关注回复 2=关键字回复, 3=默认回复]
    private Integer matchingType; // 匹配方式: [1=全匹配, 2=模糊匹配]
    private Integer contentType;  // 内容类型: [1=文本]
    private Integer status;       // 启动状态: [1=启动, 0=关闭]
    private String content;       // 回复内容
    private Integer sort;         // 排序编号
    private Integer isDelete;     // 是否删除
    private Long createTime;      // 创建时间
    private Long updateTime;      // 更新时间
    private Long deleteTime;      // 删除时间

}
