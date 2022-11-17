package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelRpDefaultValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpDefaultVo;
import com.mdd.common.core.PageResult;

/**
 * 公众号默认回复服务接口类
 */
public interface IChannelOaReplyDefaultService {

    /**
     * 默认回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelRpDefaultVo>
     */
    PageResult<ChannelRpDefaultVo> list(PageValidate pageValidate);

    /**
     * 默认回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelRpDefaultVo
     */
    ChannelRpDefaultVo detail(Integer id);

    /**
     * 默认回复新增
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    void add(ChannelRpDefaultValidate defaultValidate);

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    void edit(ChannelRpDefaultValidate defaultValidate);

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
