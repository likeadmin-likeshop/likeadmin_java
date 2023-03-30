package com.mdd.admin.service.impl;

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
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 充值记录服务实现类
 */
@Service
public class FinanceRechargerServiceImpl implements IFinanceRechargerService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

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
                ">=:payTime@pay_time:long"
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
            vo.setPayWay(PaymentEnum.getMsgByCode(Integer.parseInt(vo.getPayWay())));
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 发起退款
     */
    @Override
    public void refund() {

    }

}
