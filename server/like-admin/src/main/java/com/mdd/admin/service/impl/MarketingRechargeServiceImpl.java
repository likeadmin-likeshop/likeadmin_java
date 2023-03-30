package com.mdd.admin.service.impl;

import com.mdd.admin.service.IMarketingRechargeService;
import com.mdd.admin.validate.marketing.MarketingRechargeValidate;
import com.mdd.admin.vo.marketing.MarketingRechargeVo;
import com.mdd.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 营销充值服务实现类
 */
@Service
public class MarketingRechargeServiceImpl implements IMarketingRechargeService {

    /**
     * 充值配置详情
     *
     * @author fzr
     * @return MarketingRechargeVo
     */
    @Override
    public MarketingRechargeVo detail() {
        Map<String, String> config = ConfigUtils.get("recharge");

        MarketingRechargeVo vo = new MarketingRechargeVo();
        vo.setOpenRecharge(Integer.parseInt(config.getOrDefault("openRecharge", "0")));
        vo.setMinRechargeMoney(new BigDecimal(config.getOrDefault("minRechargeMoney", "0")));
        return vo;
    }

    /**
     * 充值配置保存
     *
     * @author fzr
     * @param rechargeValidate 充值参数
     */
    @Override
    public void save(MarketingRechargeValidate rechargeValidate) {
        ConfigUtils.set("recharge", "openRecharge", rechargeValidate.getOpenRecharge().toString());
        ConfigUtils.set("recharge", "minRechargeMoney", rechargeValidate.getMinRechargeMoney().toString());
    }

}
