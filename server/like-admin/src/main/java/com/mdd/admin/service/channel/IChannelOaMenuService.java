package com.mdd.admin.service.channel;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * 公众号菜单服务接口类
 */
public interface IChannelOaMenuService {

    /**
     * 菜单详情
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray detail();

    /**
     * 菜单保存
     *
     * @author fzr
     * @param params 参数
     * @param isPublish 是否发布
     */
    void save(List<Object> params, Boolean isPublish);

}
