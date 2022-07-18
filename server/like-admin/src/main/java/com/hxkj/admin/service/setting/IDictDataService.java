package com.hxkj.admin.service.setting;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.SettingDictDataParam;
import com.hxkj.common.core.PageResult;

import java.util.Map;

/**
 * 字典数据服务接口类
 */
public interface IDictDataService {

    PageResult<Object> list(PageParam pageParam, Map<String, String> params);

    Object detail(Integer id);

    void add(SettingDictDataParam settingDictDataParam);

    void edit(SettingDictDataParam settingDictDataParam);

    void del(Integer id);

}
