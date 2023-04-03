package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

@Data
public class SettingPaymentMethodVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String showName;

    private String icon;

    private Integer isDefault;

    private Integer status;

}
