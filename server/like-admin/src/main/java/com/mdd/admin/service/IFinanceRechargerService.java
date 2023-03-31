package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRechargeSearchValidate;
import com.mdd.admin.vo.finance.FinanceRechargeListVo;
import com.mdd.common.core.PageResult;

/**
 * 充值记录服务接口类
 */
public interface IFinanceRechargerService {

    /**
     * 充值记录
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    PageResult<FinanceRechargeListVo> list(PageValidate pageValidate, FinanceRechargeSearchValidate searchValidate);

    /**
     * 发起退款
     *
     * @author fzr
     * @param orderId 订单ID
     * @param adminId 管理员ID
     */
    void refund(Integer orderId, Integer adminId);

    /**
     * 重新退款
     *
     * @author fzr
     * @param recordId 记录ID
     * @param adminId 管理员ID
     */
    void refundAgain(Integer recordId, Integer adminId);

}
