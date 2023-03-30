package com.mdd.admin.service.impl;

import com.mdd.admin.service.IFinanceRefundService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRefundSearchValidate;
import com.mdd.admin.vo.finance.FinanceRefundListVo;
import com.mdd.common.core.PageResult;
import org.springframework.stereotype.Service;

/**
 * 退款记录服务实现类
 */
@Service
public class FinanceRefundServiceImpl implements IFinanceRefundService {

    /**
     * 退款记录列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceRechargeListVo>
     */
    @Override
    public PageResult<FinanceRefundListVo> list(PageValidate pageValidate, FinanceRefundSearchValidate searchValidate) {
        return null;
    }

}
