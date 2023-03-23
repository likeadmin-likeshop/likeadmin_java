package com.mdd.common.plugin.wechat.request;

import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RefundRequestV3 implements Serializable {

    private static final long serialVersionUID = -1L;

    /** 订单流水号 */
    private String transactionId;

    /** 订单单号 */
    private String outTradeNo;

    /** 退款单号 */
    @SerializedName("out_refund_no")
    private String outRefundNo;

    /** 退款原因 */
    private String reason;

    /** 通知接口 */
    private String notifyUrl;

    /** 退款金额 */
    private Integer refundAmount;

    /** 订单总额 */
    private Integer totalAmount;

    /** 退款币种 */
    private String currency;

    /** 商品信息 */
    private List<WxPayRefundV3Request.GoodsDetail> goodsDetails;

    /** 子商户号 */
    private String subMchid;

}
