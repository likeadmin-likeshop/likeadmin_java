package com.mdd.admin.service.channel;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * 公众号菜单服务接口类
 */
public interface IChannelOaMenuService {

    JSONArray detail();

    /**
     * 保存菜单
     *
     * @author fzr
     * @param params 参数
     * @param isPublish 是否发布
     */
    void save(List<Object> params, Boolean isPublish);

}
