package com.hxkj.admin.service.setting;

import java.util.List;
import java.util.Map;

/**
 * 网站备案服务接口类
 */
public interface ISettingCopyrightService {

    /**
     * 获取网站备案信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    List<Map<String, String>> detail();

    /**
     * 保存网站备案信息
     *
     * @author fzr
     * @param params 参数
     */
    void save(List<Map<String, String>> params);

}
