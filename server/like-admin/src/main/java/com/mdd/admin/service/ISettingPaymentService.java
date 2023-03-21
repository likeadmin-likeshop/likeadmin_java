package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingPayConfigValidate;
import com.mdd.admin.validate.setting.SettingPayMethodValidate;
import com.mdd.admin.vo.setting.SettingPaymentMethodVo;
import com.mdd.common.entity.setting.DevPayConfig;

import java.util.List;

/**
 * 支付配置服务接口类
 */
public interface ISettingPaymentService {

    List<List<SettingPaymentMethodVo>> method();

    List<DevPayConfig> list();

    void editConfig(SettingPayConfigValidate configValidate);

    void editMethod(SettingPayMethodValidate methodValidate);

}
