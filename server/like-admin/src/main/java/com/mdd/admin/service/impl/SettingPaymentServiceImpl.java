package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.ISettingPaymentService;
import com.mdd.admin.validate.setting.SettingPaymentValidate;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.util.MapUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 支付配置服务实现类
 */
@Service
public class SettingPaymentServiceImpl implements ISettingPaymentService {

    @Resource
    DevPayConfigMapper devPayConfigMapper;

    @Override
    public List<DevPayConfig> list() {
        List<DevPayConfig> devPayConfigs = devPayConfigMapper.selectList(
                new QueryWrapper<DevPayConfig>()
                    .orderByDesc(Arrays.asList("sort", "id")));

        for (DevPayConfig dev : devPayConfigs) {
            dev.setParams(MapUtils.jsonToMap(dev.getParams().toString()));
            dev.setIcon(UrlUtils.toAbsoluteUrl(dev.getIcon()));
        }

        return devPayConfigs;
    }

    @Override
    public void edit(SettingPaymentValidate paymentValidate) {
        DevPayConfig devPayConfig = devPayConfigMapper.selectOne(
                new QueryWrapper<DevPayConfig>()
                        .eq("id", paymentValidate.getId())
                        .last("limit 1"));

        Assert.notNull(devPayConfig, "数据不存在!");

        devPayConfig.setName(paymentValidate.getName());
        devPayConfig.setIcon(UrlUtils.toRelativeUrl(paymentValidate.getIcon()));
        devPayConfig.setSort(paymentValidate.getSort());
        devPayConfig.setRemark(paymentValidate.getRemark());
        devPayConfig.setParams(JSON.toJSONString(paymentValidate.getParams()));
        devPayConfigMapper.updateById(devPayConfig);
    }


}
