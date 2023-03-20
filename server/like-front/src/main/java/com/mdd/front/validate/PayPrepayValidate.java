package com.mdd.front.validate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("预支付订单参数")
public class PayPrepayValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String scene;

    private Integer orderId;

}
