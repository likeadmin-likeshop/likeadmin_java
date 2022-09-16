package com.mdd.admin.service.channel;

import com.mdd.admin.validate.common.PageParam;

import java.util.List;
import java.util.Map;

/**
 * 公众号回复服务接口类
 */
public interface IChannelOaReplyService {

    /**
     * 回复列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 参数
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> list(PageParam pageParam, Map<String, String> params);

    /**
     * 回复详情
     *
     * @author fzr
     * @param id 主键
     * @return Map<String, Object>
     */
    Map<String, Object> detail(Integer id);

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

    /**
     * 回复删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

    /**
     * 回复状态
     *
     * @author fzr
     * @param id 主键
     */
    void status(Integer id);

}
