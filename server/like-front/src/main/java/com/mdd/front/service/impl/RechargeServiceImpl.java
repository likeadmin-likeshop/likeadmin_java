package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.ToolUtils;
import com.mdd.front.service.IRechargeService;
import com.mdd.front.validate.RechargeValidate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 充值余额服务实现类
 */
@Service
public class RechargeServiceImpl implements IRechargeService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    /**
     * 创建充值订单
     *
     * @author fzr
     * @param userId 用户ID
     * @param terminal 设备端
     * @param rechargeValidate 参数
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> placeOrder(Integer userId, Integer terminal, RechargeValidate rechargeValidate) {
        RechargeOrder order = new RechargeOrder();
        order.setUserId(userId);
        order.setOrderTerminal(terminal);
        order.setOrderSn(this.randMakeOrderSn());
        order.setPayWay(rechargeValidate.getPayWay());
        order.setPayStatus(0);
        order.setRefundStatus(0);
        order.setOrderAmount(rechargeValidate.getOrderAmount());
        order.setCreateTime(System.currentTimeMillis() / 1000);
        order.setUpdateTime(System.currentTimeMillis() / 1000);
        order.setDeleteTime(System.currentTimeMillis() / 1000);
        rechargeOrderMapper.insert(order);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("orderId", order.getId());
        return response;
    }

    /**
     * 生成唯一订单号
     *
     * @author fzr
     * @return String
     */
    private String randMakeOrderSn() {
        String date = TimeUtils.timestampToDate(System.currentTimeMillis()/1000, "yyyyMMddHHmmss");
        String sn;
        while (true) {
            sn = date + ToolUtils.randomInt(12);
            RechargeOrder snModel = rechargeOrderMapper.selectOne(
                    new QueryWrapper<RechargeOrder>()
                        .select("id")
                        .eq("order_sn", sn)
                        .last("limit 1"));
            if (snModel == null) {
                break;
            }
        }
        return sn;
    }

}
