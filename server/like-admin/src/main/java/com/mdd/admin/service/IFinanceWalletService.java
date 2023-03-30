package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceWalletSearchValidate;
import com.mdd.admin.vo.finance.FinanceWalletListVo;
import com.mdd.common.core.PageResult;

/**
 * 用户余额记录服务接口类
 */
public interface IFinanceWalletService {

    PageResult<FinanceWalletListVo> list(PageValidate pageValidate, FinanceWalletSearchValidate searchValidate);

}
