package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.entity.setting.DevPayWay;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.LogMoneyEnum;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.exception.PaymentException;
import com.mdd.common.mapper.log.LogMoneyMapper;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.mapper.setting.DevPayWayMapper;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.common.plugin.wechat.request.PaymentRequestV3;
import com.mdd.common.util.*;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import com.mdd.front.vo.pay.PayStatusVo;
import com.mdd.front.vo.pay.PayWayInfoVo;
import com.mdd.front.vo.pay.PayWayListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

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
     * @param orderId 订单ID
     * @param terminal 终端
     * @return List<PayWayListedVo>
     */
    @Override
    public PayWayListVo payWay(String from, Integer orderId, Integer terminal) {
        List<DevPayWay> devPayWays = devPayWayMapper.selectList(
                new QueryWrapper<DevPayWay>()
                    .eq("scene", terminal)
                    .eq("status", 1));

        PayWayListVo vo = new PayWayListVo();
        if (from.equals("recharge")) {
            RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);
            vo.setOrderAmount(rechargeOrder.getOrderAmount());
        }

        Integer walletType = PaymentEnum.WALLET_PAY.getCode();
        List<PayWayInfoVo> list = new LinkedList<>();
        for (DevPayWay way : devPayWays) {
            if (from.equals("recharge") && way.getPayConfigId().equals(walletType)) {
                continue;
            }

            DevPayConfig devPayConfig = devPayConfigMapper.selectById(way.getPayConfigId());
            PayWayInfoVo infoVo = new PayWayInfoVo();
            infoVo.setId(devPayConfig.getId());
            infoVo.setName(devPayConfig.getName());
            infoVo.setIcon(UrlUtils.toAbsoluteUrl(devPayConfig.getIcon()));
            infoVo.setIsDefault(way.getIsDefault());
            list.add(infoVo);
        }

        vo.setList(list);
        return vo;
    }

    /**
     * 订单状态
     *
     * @author fzr
     * @param from 场景
     * @param orderId 订单ID
     * @return PayStatusVo
     */
    @Override
    public PayStatusVo payStatus(String from, Integer orderId) {
        PayStatusVo vo = new PayStatusVo();
        boolean orderExist = false;

        switch (from) {
            case "recharge":
                RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);
                if (StringUtils.isNotNull(rechargeOrder)) {
                    orderExist = true;
                    vo.setPayStatus(rechargeOrder.getPayStatus());
                    vo.setPayWay(PaymentEnum.getPayWayMsg(rechargeOrder.getPayWay()));
                    vo.setOrderId(rechargeOrder.getId());
                    vo.setOrderSn(rechargeOrder.getOrderSn());
                    vo.setOrderAmount(rechargeOrder.getOrderAmount());
                    vo.setPayTime(StringUtils.isNotEmpty(vo.getPayTime()) ? TimeUtils.timestampToDate(vo.getPayTime()) : "");
                }
                break;
            case "order":
                break;
        }

        if (!orderExist) {
            throw new OperateException("订单不存在!");
        }

        return vo;
    }

    /**
     * 发起支付
     *
     * @param params 参数
     * @param terminal 终端
     * @return Object
     */
    public Object prepay(PaymentValidate params, Integer terminal) {
        try {
            String openId = null;
            UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                    .eq("user_id", params.getUserId())
                    .eq("terminal", terminal)
                    .last("limit 1"));

            if (StringUtils.isNotNull(userAuth)) {
                openId = userAuth.getOpenid();
            }

            switch (params.getPayWay()) {
                case 1: // 余额支付
                    String attach = params.getAttach();
                    String orderSn = params.getOutTradeNo();
                    this.handlePaidNotify(attach, orderSn, null);
                    return Collections.emptyList();
                case 2: // 微信支付
                    PaymentRequestV3 requestV3 = new PaymentRequestV3();
                    requestV3.setTerminal(terminal);
                    requestV3.setOpenId(openId);
                    requestV3.setAttach(params.getAttach());
                    requestV3.setOutTradeNo(params.getOutTradeNo());
                    requestV3.setOrderAmount(params.getOrderAmount());
                    requestV3.setDescription(params.getDescription());
                    Object result = WxPayDriver.unifiedOrder(requestV3);
                    if (terminal == ClientEnum.H5.getCode()) {
                        Assert.notNull(params.getRedirectUrl(), "redirectUrl参数缺失");
                        Map<String, String> map = new LinkedHashMap<>();
                        String h5Url = result.toString();
                        h5Url += "&showCheck=true&scene="+params.getAttach()+"&redirect_url="+params.getRedirectUrl();
                        map.put("url", h5Url);
                        return map;
                    }
                    return WxPayDriver.unifiedOrder(requestV3);
            }
        } catch (Exception e) {
            throw new PaymentException(e.getMessage());
        }

        throw new PaymentException("支付发起异常");
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

            User user = userMapper.selectById(rechargeOrder.getUserId());
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
