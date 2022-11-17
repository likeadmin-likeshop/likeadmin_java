package com.mdd.admin.validate.setting;

import com.mdd.admin.vo.setting.SettingCopyrightVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 版权信息设置参数
 */
@Data
public class SettingCopyrightValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SettingCopyrightVo> list;

}
