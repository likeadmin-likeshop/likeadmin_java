package com.mdd.admin.service.setting.impl;

import com.alibaba.fastjson.JSON;
import com.mdd.admin.service.setting.ISettingCopyrightService;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 网站备案服务实现类
 */
@Service
public class SettingCopyrightServiceImpl implements ISettingCopyrightService {

    /**
     * 获取网站备案信息
     *
     * @author fzr
     * @return List<Map<String, String>>
     */
    @Override
    public List<Map<String, String>> detail() {
        String config = ConfigUtil.get("website", "copyright", "[]");
        return ArrayUtil.stringToListAsMapStr(config);
    }

    /**
     * 保存网站备案信息
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(List<Map<String, String>> params) {
        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> item : params) {
            Map<String, String> map = new LinkedHashMap<>();
            if (StringUtil.isNotEmpty(item.get("name")) || StringUtil.isNotEmpty(item.get("link"))) {
                map.put("name", item.get("name"));
                map.put("link", item.get("link"));
                list.add(map);
            }
        }
        ConfigUtil.set("website", "copyright", JSON.toJSONString(list));
    }

}
