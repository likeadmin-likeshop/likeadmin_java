package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelRpKeywordValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpKeywordVo;
import com.mdd.common.core.PageResult;

/**
 * 公众号关键词回复服务接口类
 */
public interface IChannelOaReplyKeywordService {

    /**
     * 关键词回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelRpKeywordVo>
     */
    PageResult<ChannelRpKeywordVo> list(PageValidate pageValidate);

    /**
     * 关键词回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelRpKeywordVo
     */
    ChannelRpKeywordVo detail(Integer id);


    /**
     * 关键词回复新增
     *
     * @author fzr
     * @param keywordValidate 参数
     */
    void add(ChannelRpKeywordValidate keywordValidate);

    /**
     * 关键词回复编辑
     *
     * @author fzr
     * @param keywordValidate 参数
     */
    void edit(ChannelRpKeywordValidate keywordValidate);

    /**
     * 关键词回复删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

    /**
     * 关键词回复状态
     *
     * @author fzr
     * @param id 主键
     */
    void status(Integer id);

}
