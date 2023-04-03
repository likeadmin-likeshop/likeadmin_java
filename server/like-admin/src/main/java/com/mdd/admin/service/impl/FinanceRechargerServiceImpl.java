package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.service.IFinanceRechargerService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRechargeSearchValidate;
import com.mdd.admin.vo.finance.FinanceRechargeListVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.entity.RefundLog;
import com.mdd.common.entity.RefundRecord;
import com.mdd.common.entity.user.User;
import com.mdd.common.enums.LogMoneyEnum;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.enums.RefundEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.mapper.RefundLogMapper;
import com.mdd.common.mapper.RefundRecordMapper;
import com.mdd.common.mapper.log.LogMoneyMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.common.plugin.wechat.request.RefundRequestV3;
import com.mdd.common.util.AmountUtil;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 充值记录服务实现类
 */
@Service
public class FinanceRechargerServiceImpl implements IFinanceRechargerService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    LogMoneyMapper logMoneyMapper;

    @Resource
    RefundRecordMapper refundRecordMapper;

    @Resource
    RefundLogMapper refundLogMapper;

    @Resource
    DataSourceTransactionManager transactionManager ;

    @Resource
    TransactionDefinition transactionDefinition;

    /**
     * 充值记录
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    @Override
    public PageResult<FinanceRechargeListVo> list(PageValidate pageValidate, FinanceRechargeSearchValidate searchValidate) {
        Integer pageNo = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        MPJQueryWrapper<RechargeOrder> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(RechargeOrder.class)
                .select("U.id as user_id,U.sn as user_sn,U.nickname,U.avatar")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        rechargeOrderMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:sn@t.order_sn:str",
                "=:payWay@t.pay_way:int",
                "=:payStatus@t.pay_status:int",
                "datetime:startTime-endTime@create_time:long",
        });

        if (StringUtils.isNotEmpty(searchValidate.getKeyword())) {
            String keyword = searchValidate.getKeyword();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }

        IPage<FinanceRechargeListVo> iPage = rechargeOrderMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                FinanceRechargeListVo.class,
                mpjQueryWrapper);

        for (FinanceRechargeListVo vo : iPage.getRecords()) {
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setPayTime(TimeUtils.timestampToDate(vo.getPayTime()));
            vo.setAvatar(UrlUtils.toAbsoluteUrl(vo.getAvatar()));
            vo.setPayWay(PaymentEnum.getPayWayMsg(Integer.parseInt(vo.getPayWay())));

            vo.setIsRefund(0);
            if (vo.getPayStatus().equals(1)) {
                RefundRecord refundRecord = refundRecordMapper.selectOne(
                        new QueryWrapper<RefundRecord>()
                                .eq("order_type", "recharge")
                                .eq("order_id", vo.getId())
                                .last("limit 1"));
                if (StringUtils.isNotNull(refundRecord)) {
                    vo.setIsRefund(1);
                    vo.setRefundStatusMsg(RefundEnum.getRefundStatusMsg(refundRecord.getRefundStatus()));
                }
            }
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 发起退款
     *
     * @author fzr
     * @param orderId 订单ID
     * @param adminId 管理员ID
     */
    @Override
    public void refund(Integer orderId, Integer adminId) {
        RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);

        Assert.notNull(rechargeOrder, "充值订单不存在!");
        if (!rechargeOrder.getPayStatus().equals(PaymentEnum.OK_PAID.getCode())) {
            throw new OperateException("当前订单不可退款!");
        }

        if (rechargeOrder.getRefundStatus().equals(1)) {
            throw new OperateException("订单已发起退款,退款失败请到退款记录重新退款!");
        }

        User user = userMapper.selectById(rechargeOrder.getUserId());
        if (user.getMoney().compareTo(rechargeOrder.getOrderAmount()) < 0) {
            throw new OperateException("退款失败:用户余额已不足退款金额!");
        }

        // 开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        RefundRecord refundRecord = null;
        RefundLog log = null;
        try {
            // 标记退款状态
            rechargeOrder.setRefundStatus(1);
            rechargeOrderMapper.updateById(rechargeOrder);

            // 更新用户余额
            user.setMoney(user.getMoney().subtract(rechargeOrder.getOrderAmount()));
            userMapper.updateById(user);

            // 记录余额日志
            logMoneyMapper.dec(
                    user.getId(),
                    LogMoneyEnum.UM_DEC_RECHARGE.getCode(),
                    rechargeOrder.getOrderAmount(),
                    rechargeOrder.getId(),
                    rechargeOrder.getOrderSn(),
                    "充值订单退款",
                    null
            );

            // 生成退款记录
            String refundSn = refundRecordMapper.randMakeOrderSn("sn");
            refundRecord = new RefundRecord();
            refundRecord.setSn(refundSn);
            refundRecord.setUserId(rechargeOrder.getUserId());
            refundRecord.setOrderId(rechargeOrder.getId());
            refundRecord.setOrderSn(rechargeOrder.getOrderSn());
            refundRecord.setOrderType(RefundEnum.getOrderType(RefundEnum.ORDER_TYPE_RECHARGE.getCode()));
            refundRecord.setOrderAmount(rechargeOrder.getOrderAmount());
            refundRecord.setRefundAmount(rechargeOrder.getOrderAmount());
            refundRecord.setRefundType(RefundEnum.TYPE_ADMIN.getCode());
            refundRecord.setTransactionId(refundRecord.getTransactionId());
            refundRecord.setRefundWay(rechargeOrder.getPayWay());
            refundRecordMapper.insert(refundRecord);

            // 生成退款日志
            log = new RefundLog();
            log.setSn(refundLogMapper.randMakeOrderSn("sn"));
            log.setRecordId(refundRecord.getId());
            log.setUserId(rechargeOrder.getUserId());
            log.setHandleId(adminId);
            log.setOrderAmount(rechargeOrder.getOrderAmount());
            log.setRefundAmount(refundRecord.getRefundAmount());
            log.setRefundStatus(RefundEnum.REFUND_ING.getCode());
            refundLogMapper.insert(log);

            // 发起退款请求
            RefundRequestV3 requestV3 = new RefundRequestV3();
            requestV3.setTransactionId(rechargeOrder.getTransactionId());
            requestV3.setOutTradeNo(rechargeOrder.getOrderSn());
            requestV3.setOutRefundNo(refundSn);
            requestV3.setTotalAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
            requestV3.setRefundAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
            WxPayDriver.refund(requestV3);

            // 退款记录更新
            refundRecord.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            refundRecordMapper.updateById(refundRecord);

            // 退款日志更新
            log.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            refundLogMapper.updateById(log);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 事务回滚
            transactionManager.rollback(transactionStatus);

            if (StringUtils.isNotNull(refundRecord)) {
                refundRecord.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundRecordMapper.updateById(refundRecord);
            }

            if (StringUtils.isNotNull(log)) {
                log.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundLogMapper.updateById(log);
            }
            throw new OperateException(e.getMessage());
        }
    }

    /**
     * 重新退款
     *
     * @author fzr
     * @param recordId 记录ID
     * @param adminId 管理员ID
     */
    @Override
    public void refundAgain(Integer recordId, Integer adminId) {
        // 开启事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        RefundLog log = null;
        try {
            RefundRecord refundRecord = refundRecordMapper.selectById(recordId);
            RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(refundRecord.getOrderId());

            Assert.notNull(rechargeOrder, "充值订单丢失!");

            log = refundLogMapper.selectOne(new QueryWrapper<RefundLog>()
                    .eq("record_id", recordId)
                    .last("limit 1"));

            log.setRefundStatus(RefundEnum.REFUND_ING.getCode());
            refundLogMapper.updateById(log);

            // 发起退款请求
            RefundRequestV3 requestV3 = new RefundRequestV3();
            requestV3.setTransactionId(refundRecord.getTransactionId());
            requestV3.setOutTradeNo(refundRecord.getOrderSn());
            requestV3.setOutRefundNo(refundRecord.getSn());
            requestV3.setTotalAmount(AmountUtil.yuan2Fen(rechargeOrder.getOrderAmount().toString()));
            requestV3.setRefundAmount(AmountUtil.yuan2Fen(refundRecord.getOrderAmount().toString()));
            WxPayDriver.refund(requestV3);

            log.setRefundStatus(RefundEnum.REFUND_SUCCESS.getCode());
            refundLogMapper.updateById(log);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            if (StringUtils.isNotNull(log)) {
                log.setRefundStatus(RefundEnum.REFUND_ERROR.getCode());
                refundLogMapper.updateById(log);
            }
            throw new OperateException(e.getMessage());
        }
    }

}
