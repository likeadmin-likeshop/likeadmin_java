package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.service.IFinanceWalletService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceWalletSearchValidate;
import com.mdd.admin.vo.finance.FinanceWalletListVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.log.LogMoney;
import com.mdd.common.enums.LogMoneyEnum;
import com.mdd.common.mapper.log.LogMoneyMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户余额记录服务实现类
 */
@Service
public class FinanceWalletServiceImpl implements IFinanceWalletService {

    @Resource
    LogMoneyMapper logMoneyMapper;

    @Override
    public PageResult<FinanceWalletListVo> list(PageValidate pageValidate, FinanceWalletSearchValidate searchValidate) {
        Integer pageNo = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        MPJQueryWrapper<LogMoney> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(LogMoney.class)
                .select("U.id as user_id,U.sn as user_sn,U.nickname,U.avatar")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        logMoneyMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "=:type@change_type:int",
                "datetime:startTime-endTime@create_time:long",
        });

        if (StringUtils.isNotEmpty(searchValidate.getKeyword())) {
            String keyword = searchValidate.getKeyword();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }

        IPage<FinanceWalletListVo> iPage = logMoneyMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                FinanceWalletListVo.class,
                mpjQueryWrapper);

        for (FinanceWalletListVo vo : iPage.getRecords()) {
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setChangeType(LogMoneyEnum.getMsgByCode(Integer.parseInt(vo.getChangeType())));
            vo.setAvatar(UrlUtils.toAbsoluteUrl(vo.getAvatar()));
        }

        Map<String, Object> extend = new LinkedHashMap<>();
        extend.put("changeType", LogMoneyEnum.getTypeList());

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords(), extend);
    }

}
