package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingPaymentValidate;
import com.mdd.common.entity.setting.DevPayConfig;

import java.util.List;

/**
 * 支付配置服务接口类
 */
public interface ISettingPaymentService {

    List<DevPayConfig> list();

    void edit(SettingPaymentValidate paymentValidate);

}
