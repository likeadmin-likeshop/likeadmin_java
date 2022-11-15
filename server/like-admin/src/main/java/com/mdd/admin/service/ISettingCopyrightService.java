package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingCopyrightValidate;
import com.mdd.admin.vo.setting.SettingCopyrightVo;

import java.util.List;

/**
 * 网站备案服务接口类
 */
public interface ISettingCopyrightService {

    /**
     * 获取网站备案信息
     *
     * @author fzr
     * @return List<SettingCopyrightVo>
     */
    List<SettingCopyrightVo> detail();

    /**
     * 保存网站备案信息
     *
     * @author fzr
     * @param copyrightValidate 参数
     */
    void save(SettingCopyrightValidate copyrightValidate);

}
