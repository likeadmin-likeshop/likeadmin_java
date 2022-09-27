package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelOaReplyParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.channel.ChannelOaReplyVo;
import com.mdd.common.core.PageResult;

import java.util.List;

/**
 * 公众号默认回复服务接口类
 */
public interface IChannelOaReplyDefaultService {

    /**
     * 默认回复列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<ChannelOaReplyVo>
     */
    PageResult<ChannelOaReplyVo> list(PageParam pageParam);

    /**
     * 默认回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelOaReplyDefaultVo
     */
    ChannelOaReplyVo detail(Integer id);

    /**
     * 默认回复新增
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    void add(ChannelOaReplyParam channelOaReplyParam);

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    void edit(ChannelOaReplyParam channelOaReplyParam);

    /**
     * 默认回复删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

    /**
     * 默认回复状态
     *
     * @author fzr
     * @param id 主键
     */
    void status(Integer id);

}
