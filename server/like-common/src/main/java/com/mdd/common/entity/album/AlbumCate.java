package com.mdd.common.entity.album;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 相册分类实体
 */
@Data
public class AlbumCate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;        // 主键ID
    private Integer pid;       // 父级ID
    private Integer type;      // 分类类型: [10=图片,20=视频]
    private String name;       // 分类名称
    private Integer isDelete;  // 是否删除: [0=否,1=是]
    private Long createTime;   // 创建时间
    private Long updateTime;   // 更新时间
    private Long deleteTime;   // 删除时间

}
