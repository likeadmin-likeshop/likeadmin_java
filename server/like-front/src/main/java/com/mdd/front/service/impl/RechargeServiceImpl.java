package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.ToolUtils;
import com.mdd.front.service.IRechargeService;
import com.mdd.front.validate.RechargeValidate;
import com.mdd.front.validate.common.PageValidate;
import com.mdd.front.vo.RechargeRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 充值余额服务实现类
 */
@Service
public class RechargeServiceImpl implements IRechargeService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    /**
     * 充值记录
     *
     * @author fzr
     * @param userId 用户ID
     * @param pageValidate 分页参数
     * @return PageResult<RechargeRecordVo>
     */
    @Override
    public PageResult<RechargeRecordVo> record(Integer userId, PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        QueryWrapper<RechargeOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("pay_status", PaymentEnum.OK_PAID.getCode());
        queryWrapper.orderByDesc("id");

        IPage<RechargeOrder> iPage = rechargeOrderMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<RechargeRecordVo> list = new LinkedList<>();
        for (RechargeOrder rechargeOrder : iPage.getRecords()) {
            RechargeRecordVo vo = new RechargeRecordVo();
            vo.setId(rechargeOrder.getId());
            vo.setOrderAmount(rechargeOrder.getOrderAmount());
            vo.setPayTime(TimeUtils.timestampToDate(rechargeOrder.getPayTime()));
            vo.setTips("充值" + vo.getOrderAmount() + "元");
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

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
        order.setOrderSn(rechargeOrderMapper.randMakeOrderSn("order_sn"));
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

}
