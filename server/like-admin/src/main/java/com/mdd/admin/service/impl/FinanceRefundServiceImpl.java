package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.service.IFinanceRefundService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRefundSearchValidate;
import com.mdd.admin.vo.finance.FinanceRefundListVo;
import com.mdd.admin.vo.finance.FinanceRefundLogVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.RefundLog;
import com.mdd.common.entity.RefundRecord;
import com.mdd.common.enums.RefundEnum;
import com.mdd.common.mapper.RefundLogMapper;
import com.mdd.common.mapper.RefundRecordMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 退款记录服务实现类
 */
@Service
public class FinanceRefundServiceImpl implements IFinanceRefundService {

    @Resource
    RefundRecordMapper refundRecordMapper;

    @Resource
    RefundLogMapper refundLogMapper;

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
        Integer pageNo = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        MPJQueryWrapper<RefundRecord> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(RefundRecord.class)
                .select("U.id as user_id,U.nickname,U.avatar")
                .leftJoin("?_user U ON U.id=t.user_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        refundRecordMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:sn@t.sn:str",
                "like:orderSn@t.t.order_sn:str",
                "=:refundType@t.refund_type:int",
                "=:refundStatus@t.refund_status:int",
                "datetime:startTime-endTime@create_time:long",
        });

        if (StringUtils.isNotNull(searchValidate.getType()) && searchValidate.getType() != -1) {
            mpjQueryWrapper.eq("refund_status", searchValidate.getType());
        }

        if (StringUtils.isNotEmpty(searchValidate.getKeyword())) {
            String keyword = searchValidate.getKeyword();
            mpjQueryWrapper.nested(wq->wq
                    .like("U.nickname", keyword).or()
                    .like("U.sn", keyword).or()
                    .like("U.mobile", keyword));
        }

        IPage<FinanceRefundListVo> iPage = refundRecordMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                FinanceRefundListVo.class,
                mpjQueryWrapper);

        for (FinanceRefundListVo vo : iPage.getRecords()) {
            vo.setRefundTypeMsg(RefundEnum.getRefundTypeMsg(vo.getRefundType()));
            vo.setRefundStatusMsg(RefundEnum.getRefundStatusMsg(vo.getRefundStatus()));
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
        }

        Map<String, Object> extend = new LinkedHashMap<>();
        extend.put("total", refundRecordMapper.selectCount(null));
        extend.put("ing", refundRecordMapper.selectCount(new QueryWrapper<RefundRecord>().eq("refund_status", 0)));
        extend.put("success", refundRecordMapper.selectCount(new QueryWrapper<RefundRecord>().eq("refund_status", 1)));
        extend.put("error", refundRecordMapper.selectCount(new QueryWrapper<RefundRecord>().eq("refund_status", 2)));

        Map<String, Object> stat = new LinkedHashMap<>();
        stat.put("totalRefundAmount", refundRecordMapper.sum("order_amount", new QueryWrapper<>()));
        stat.put("ingRefundAmount", refundRecordMapper.sum("order_amount", new QueryWrapper<RefundRecord>().eq("refund_status", 0)));
        stat.put("successRefundAmount", refundRecordMapper.sum("order_amount", new QueryWrapper<RefundRecord>().eq("refund_status", 1)));
        stat.put("errorRefundAmount", refundRecordMapper.sum("order_amount", new QueryWrapper<RefundRecord>().eq("refund_status", 2)));
        extend.put("stat", stat);

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords(), extend);
    }

    /**
     * 退款日志
     *
     * @author fzr
     * @param recordId 记录ID
     * @return List<FinanceRefundLogVo>
     */
    @Override
    public List<FinanceRefundLogVo> log(Integer recordId) {
        MPJQueryWrapper<RefundLog> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.selectAll(RefundLog.class)
                .select("sa.nickname as handler")
                .eq("t.record_id", recordId)
                .innerJoin("?_system_auth_admin sa ON sa.id=t.handle_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("t.id");

        List<FinanceRefundLogVo> list = refundLogMapper.selectJoinList(FinanceRefundLogVo.class, mpjQueryWrapper);

        for (FinanceRefundLogVo vo : list) {
            vo.setRefundStatusMsg(RefundEnum.getRefundStatusMsg(vo.getRefundStatus()));
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
        }

        return list;
    }

}
