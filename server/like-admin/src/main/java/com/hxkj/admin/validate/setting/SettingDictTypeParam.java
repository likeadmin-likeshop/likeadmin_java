package com.hxkj.admin.validate.setting;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 字典类型参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SettingDictTypeParam implements Serializable {

    private static final long serialVersionUID = 1L;


}
