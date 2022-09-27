package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelOaReplyParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.channel.ChannelOaReplyVo;
import com.mdd.common.core.PageResult;

import java.util.List;

/**
 * 公众号关注回复服务接口类
 */
public interface IChannelOaReplyFollowService {

    /**
     * 关注回复列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<ChannelOaReplyVo>
     */
    PageResult<ChannelOaReplyVo> list(PageParam pageParam);

    /**
     * 关注回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelOaReplyFollowVo
     */
    ChannelOaReplyVo detail(Integer id);

    /**
     * 关注回复新增
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    void add(ChannelOaReplyParam channelOaReplyParam);

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    void edit(ChannelOaReplyParam channelOaReplyParam);

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
