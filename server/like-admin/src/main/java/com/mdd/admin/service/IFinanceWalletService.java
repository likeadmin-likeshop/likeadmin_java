package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceWalletSearchValidate;
import com.mdd.admin.vo.finance.FinanceWalletListVo;
import com.mdd.common.core.PageResult;

/**
 * 用户余额记录服务接口类
 */
public interface IFinanceWalletService {

    /**
     * 余额明细列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<FinanceWalletListVo>
     */
    PageResult<FinanceWalletListVo> list(PageValidate pageValidate, FinanceWalletSearchValidate searchValidate);

}
