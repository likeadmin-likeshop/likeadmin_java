package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("退款日志实体")
public class RefundLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("编号")
    private String sn;

    @ApiModelProperty("退款记录ID")
    private Integer recordId;

    @ApiModelProperty("关联用户ID")
    private Integer userId;

    @ApiModelProperty("处理管理ID")
    private Integer handleId;

    @ApiModelProperty("总应付的金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("本次退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款状态: [0=退款中, 1=退款成功, 2=退款失败]")
    private Integer refundStatus;

    @ApiModelProperty("退款信息")
    private String refundMsg;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

}
