package com.hxkj.admin.service.setting.impl;

import com.alibaba.fastjson.JSON;
import com.hxkj.admin.service.setting.ISettingProtocolService;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
     * @return Map<String, Map<String, String>>
     */
    @Override
    public Map<String, Map<String, String>> detail() {
        String service = ConfigUtil.get("protocol", "service", "{\"name\":\"\",\"content\":\"\"}");
        String privacy = ConfigUtil.get("protocol", "privacy", "{\"name\":\"\",\"content\":\"\"}");

        Map<String, Map<String, String>> map = new LinkedHashMap<>();
        map.put("service", ToolsUtil.jsonToMap(service));
        map.put("privacy", ToolsUtil.jsonToMap(privacy));
        return map;
    }

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, Object> params) {
        ConfigUtil.set("protocol","service", JSON.toJSONString(params.get("service")));
        ConfigUtil.set("protocol","privacy", JSON.toJSONString(params.get("privacy")));
    }

}
