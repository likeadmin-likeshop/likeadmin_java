package com.mdd.admin.service;

import com.mdd.admin.validate.marketing.MarketingRechargeValidate;
import com.mdd.admin.vo.marketing.MarketingRechargeVo;

/**
 * 营销充值服务接口类
 */
public interface IMarketingRechargeService {

    /**
     * 充值配置详情
     *
     * @author fzr
     * @return MarketingRechargeVo
     */
    MarketingRechargeVo detail();

    /**
     * 充值配置保存
     *
     * @author fzr
     * @param rechargeValidate 充值参数
     */
    void save(MarketingRechargeValidate rechargeValidate);

}
