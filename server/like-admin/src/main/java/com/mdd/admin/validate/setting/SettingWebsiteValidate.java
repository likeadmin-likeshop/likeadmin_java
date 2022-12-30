package com.mdd.admin.validate.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础设置参数
 */
@Data
public class SettingWebsiteValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name = "";
    private String logo = "";
    private String favicon = "";
    private String backdrop = "";
    private String shopName = "";
    private String shopLogo = "";

    private String pcLogo = "";
    private String pcTitle = "";
    private String pcIco = "";
    private String pcDesc = "";
    private String pcKeywords = "";

}
