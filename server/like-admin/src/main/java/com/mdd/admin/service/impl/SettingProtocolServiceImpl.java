package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.mdd.admin.service.ISettingProtocolService;
import com.mdd.admin.validate.setting.SettingProtocolValidate;
import com.mdd.admin.vo.setting.SettingProtocolDetailVo;
import com.mdd.admin.vo.setting.SettingProtocolObjectVo;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 政策接口服务实现类
 */
@Service
public class SettingProtocolServiceImpl implements ISettingProtocolService {

    /**
     * 获取政策协议信息
     *
     * @author fzr
     * @return SettingProtocolDetailVo
     */
    @Override
    public SettingProtocolDetailVo detail() {
        String service = ConfigUtils.get("protocol", "service", "{\"name\":\"\",\"content\":\"\"}");
        String privacy = ConfigUtils.get("protocol", "privacy", "{\"name\":\"\",\"content\":\"\"}");
        Map<String, String> serviceMap = MapUtils.jsonToMap(service);
        Map<String, String> privacyMap = MapUtils.jsonToMap(privacy);

        SettingProtocolObjectVo serviceObj = new SettingProtocolObjectVo();
        serviceObj.setName(serviceMap.getOrDefault("name", ""));
        serviceObj.setContent(serviceMap.getOrDefault("content", ""));

        SettingProtocolObjectVo privacyObj = new SettingProtocolObjectVo();
        privacyObj.setName(privacyMap.getOrDefault("name", ""));
        privacyObj.setContent(privacyMap.getOrDefault("content", ""));

        SettingProtocolDetailVo vo = new SettingProtocolDetailVo();
        vo.setService(serviceObj);
        vo.setPrivacy(privacyObj);
        return vo;
    }

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param protocolValidate 参数
     */
    @Override
    public void save(SettingProtocolValidate protocolValidate) {
        ConfigUtils.set("protocol","service", JSON.toJSONString(protocolValidate.getService()));
        ConfigUtils.set("protocol","privacy", JSON.toJSONString(protocolValidate.getPrivacy()));
    }

}
