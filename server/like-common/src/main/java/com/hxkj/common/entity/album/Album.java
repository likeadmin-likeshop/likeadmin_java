package com.hxkj.common.entity.album;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 相册实体
 */
@Data
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        // 主键ID
    private Integer cid;       // 类目ID
    private Integer aid;       // 管理ID
    private Integer uid;       // 用户ID
    private Integer type;      // 文件类型: [10=图片, 20=视频]
    private String name;       // 文件名称
    private String uri;        // 文件路径
    private String ext;        // 文件扩展
    private Long size;         // 文件大小
    private Integer isDelete;  // 是否删除: [0=否, 1=是]
    private Long createTime;   // 创建时间
    private Long updateTime;   // 更新时间
    private Long deleteTime;   // 删除时间

}
