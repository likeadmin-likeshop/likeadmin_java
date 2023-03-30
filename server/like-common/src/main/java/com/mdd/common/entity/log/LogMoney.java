package com.mdd.common.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("账户变动实体")
public class LogMoney {

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("流水号")
    private String sn;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("关联ID")
    private Integer sourceId;

    @ApiModelProperty("关联单号")
    private String sourceSn;

    @ApiModelProperty("变动类型")
    private Integer changeType;

    @ApiModelProperty("变动数量")
    private BigDecimal changeAmount;

    @ApiModelProperty("变动后数量")
    private BigDecimal leftAmount;

    @ApiModelProperty("变动类型: [1=增加, 2=减少]")
    private Integer action;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("预留字段")
    private String extra;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
