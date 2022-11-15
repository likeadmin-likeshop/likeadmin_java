package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingProtocolValidate;
import com.mdd.admin.vo.setting.SettingProtocolDetailVo;

import java.util.Map;

/**
 * 政策协议服务接口类
 */
public interface ISettingProtocolService {

    /**
     * 获取政策协议信息
     *
     * @author fzr
     * @return SettingProtocolDetailVo
     */
    SettingProtocolDetailVo detail();

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param protocolValidate 参数
     */
    void save(SettingProtocolValidate protocolValidate);

}
