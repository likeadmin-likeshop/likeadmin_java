package com.mdd.admin.service.channel;

import java.util.Map;

public interface IChannelOaReplyService {

    Object list();

    Object detail();

    /**
     * 回复新增
     *
     * @author fzr
     * @param params 参数
     */
    void add(Map<String, String> params);

    /**
     * 回复编辑
     *
     * @author fzr
     * @param params 参数
     */
    void edit(Map<String, String> params);

    void del();

}
