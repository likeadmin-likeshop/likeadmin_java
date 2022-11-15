package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelRpFollowsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpFollowsVo;
import com.mdd.common.core.PageResult;

/**
 * 公众号关注回复服务接口类
 */
public interface IChannelOaReplyFollowService {

    /**
     * 关注回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelRpFollowsVo>
     */
    PageResult<ChannelRpFollowsVo> list(PageValidate pageValidate);

    /**
     * 关注回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelRpFollowsVo
     */
    ChannelRpFollowsVo detail(Integer id);

    /**
     * 关注回复新增
     *
     * @author fzr
     * @param followsValidate 参数
     */
    void add(ChannelRpFollowsValidate followsValidate);

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param followsValidate 参数
     */
    void edit(ChannelRpFollowsValidate followsValidate);

    /**
     * 关注回复删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

    /**
     * 关注回复状态
     *
     * @author fzr
     * @param id 主键
     */
    void status(Integer id);

}
