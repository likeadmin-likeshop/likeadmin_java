package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRefundSearchValidate;
import com.mdd.admin.vo.finance.FinanceRefundListVo;
import com.mdd.admin.vo.finance.FinanceRefundLogVo;
import com.mdd.common.core.PageResult;

import java.util.List;

/**
 * 退款记录服务接口类
 */
public interface IFinanceRefundService {

    /**
     * 退款记录列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    PageResult<FinanceRefundListVo> list(PageValidate pageValidate, FinanceRefundSearchValidate searchValidate);

    /**
     * 退款日志
     *
     * @param recordId 退款日志ID
     * @return  List<FinanceRefundLogVo>
     */
    List<FinanceRefundLogVo> log(Integer recordId);

}
