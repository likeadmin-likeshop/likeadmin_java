package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.entity.setting.DevPayWay;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.LogMoneyEnum;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.mapper.LogMoneyMapper;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.mapper.setting.DevPayWayMapper;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.common.util.*;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import com.mdd.front.vo.PayWayListedVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class PayServiceImpl implements IPayService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserAuthMapper userAuthMapper;

    @Resource
    DevPayWayMapper devPayWayMapper;

    @Resource
    DevPayConfigMapper devPayConfigMapper;

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    LogMoneyMapper logMoneyMapper;

    /**
     * 支付方式
     *
     * @author fzr
     * @param from 场景
     * @param terminal 总端
     * @return List<PayWayListedVo>
     */
    @Override
    public List<PayWayListedVo> payWay(String from, Integer terminal) {
        List<DevPayWay> devPayWays = devPayWayMapper.selectList(
                new QueryWrapper<DevPayWay>()
                    .eq("scene", terminal)
                    .eq("status", 1));

        Integer walletType = PaymentEnum.WALLET_PAY.getCode();
        List<PayWayListedVo> list = new LinkedList<>();

        for (DevPayWay way : devPayWays) {
            if (from.equals("recharge") && way.getPayConfigId().equals(walletType)) {
                continue;
            }

            DevPayConfig devPayConfig = devPayConfigMapper.selectById(way.getPayConfigId());
            PayWayListedVo vo = new PayWayListedVo();
            vo.setId(devPayConfig.getId());
            vo.setName(devPayConfig.getName());
            vo.setIcon(UrlUtils.toAbsoluteUrl(devPayConfig.getIcon()));
            vo.setIsDefault(way.getIsDefault());
            list.add(vo);
        }

        return list;
    }

    /**
     * 微信支付
     *
     * @author fzr
     * @param params 支付参数
     * @param terminal 终端
     * @throws Exception 异常
     */
    @Override
    public WxPayUnifiedOrderV3Result.JsapiResult wxPay(PaymentValidate params, Integer terminal) throws Exception {
        // 订单参数
        Integer userId  = params.getUserId();
        String attach   = params.getAttach();
        String orderSn  = params.getOrderSn();
        BigDecimal orderAmount = params.getOrderAmount();
        String description = params.getDescription();

        // 查询OpenId
        String openId = "";
        UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                .eq("user_id", userId)
                .eq("terminal", terminal)
                .last("limit 1"));

        // 设置OpenId
        if (StringUtils.isNotNull(userAuth)) {
            openId = userAuth.getOpenid();
        }

        // 失效时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Long expireTime = System.currentTimeMillis() + (30 * 60 * 1000);
        String timeExpire = format.format(expireTime) + "+08:00";

        // 订单信息
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        wxPayUnifiedOrderV3Request.setOutTradeNo(orderSn);
        wxPayUnifiedOrderV3Request.setDescription(description);
        wxPayUnifiedOrderV3Request.setTimeExpire(timeExpire);
        wxPayUnifiedOrderV3Request.setAttach(attach);
        wxPayUnifiedOrderV3Request.setNotifyUrl(RequestUtils.uri() + "/api/pay/notifyMnp");

        // 订单金额
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setTotal(AmountUtil.yuan2Fen(orderAmount.toPlainString()));
        amount.setCurrency("CNY");
        wxPayUnifiedOrderV3Request.setAmount(amount);

        // 付款人员
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid(openId);

        // H5平台
        if (terminal == ClientEnum.H5.getCode()) {
            WxPayUnifiedOrderV3Request.SceneInfo sceneInfo = new WxPayUnifiedOrderV3Request.SceneInfo();
            WxPayUnifiedOrderV3Request.H5Info h5Info = new WxPayUnifiedOrderV3Request.H5Info();
            h5Info.setType("android");
            sceneInfo.setH5Info(h5Info);
            sceneInfo.setPayerClientIp(IpUtils.getHostIp());
            sceneInfo.setDeviceId("1");
            wxPayUnifiedOrderV3Request.setSceneInfo(sceneInfo);
        }

        // 发起订单
        WxPayService wxPayService = WxPayDriver.handler(terminal);
        wxPayUnifiedOrderV3Request.setPayer(payer);
        return wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderV3Request);
    }

    /**
     * 支付回调处理
     *
     * @author fzr
     * @param attach 场景码
     * @param outTradeNo 订单编号
     * @param transactionId 流水号
     */
    @Override
    @Transactional
    public void handlePaidNotify(String attach, String outTradeNo, String transactionId) {
        switch (attach) {
            case "order":
                break;
            case "recharge":
                this.rechargeCallback(outTradeNo, transactionId);
                break;
        }
    }

    /**
     * 余额充值回调
     *
     * @author fzr
     * @param outTradeNo 订单号
     * @param transactionId 流水号
     */
    private void rechargeCallback(String outTradeNo, String transactionId) {
        for (int i=0; i<=0; i++) {
            RechargeOrder rechargeOrder = rechargeOrderMapper.selectOne(
                    new QueryWrapper<RechargeOrder>()
                            .eq("order_sn", outTradeNo)
                            .last("limit 1"));

            if (StringUtils.isNull(rechargeOrder)) {
                log.error("充值订单不存在: {} : {}", outTradeNo, transactionId);
                break;
            }

            if (rechargeOrder.getPayStatus().equals(PaymentEnum.OK_PAID.getCode())) {
                log.error("充值订单已支付: {} : {}", outTradeNo, transactionId);
                break;
            }

            rechargeOrder.setPayStatus(1);
            rechargeOrder.setTransactionId(transactionId);
            rechargeOrder.setPayTime(System.currentTimeMillis() / 1000);
            rechargeOrder.setUpdateTime(System.currentTimeMillis() / 1000);
            rechargeOrderMapper.updateById(rechargeOrder);

            User user = userMapper.selectById(rechargeOrder.getId());
            user.setMoney(user.getMoney().add(rechargeOrder.getOrderAmount()));
            user.setUpdateTime(System.currentTimeMillis() / 1000);
            userMapper.updateById(user);

            logMoneyMapper.add(rechargeOrder.getUserId(),
                    LogMoneyEnum.UM_INC_RECHARGE.getCode(),
                    rechargeOrder.getOrderAmount(),
                    rechargeOrder.getId(),
                    rechargeOrder.getOrderSn(),
                    "用户充值余额", null);
        }
    }

}
