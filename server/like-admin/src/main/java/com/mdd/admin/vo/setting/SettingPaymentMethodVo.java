package com.mdd.admin.vo.setting;

import lombok.Data;

@Data
public class SettingPaymentMethodVo {

    private Integer id;

    private String name;

    private String showName;

    private String icon;

    private Integer isDefault;

    private Integer status;

}
