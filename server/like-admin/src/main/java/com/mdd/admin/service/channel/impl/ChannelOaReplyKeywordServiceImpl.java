package com.mdd.admin.service.channel.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.channel.IChannelOaReplyKeywordService;
import com.mdd.admin.validate.channel.ChannelOaReplyParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.channel.ChannelOaReplyVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.OfficialReply;
import com.mdd.common.mapper.OfficialReplyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 公众号键词回复服务实现类
 */
@Service
public class ChannelOaReplyKeywordServiceImpl implements IChannelOaReplyKeywordService {

    @Resource
    OfficialReplyMapper officialReplyMapper;

    /**
     * 关键词回复列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return List<ChannelOaReplyVo>
     */
    @Override
    public PageResult<ChannelOaReplyVo> list(PageParam pageParam) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        QueryWrapper<OfficialReply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reply_type", 2);
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));
        IPage<OfficialReply> iPage = officialReplyMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<ChannelOaReplyVo> list = new LinkedList<>();
        for (OfficialReply officialReply : iPage.getRecords()) {
            ChannelOaReplyVo vo = new ChannelOaReplyVo();
            BeanUtils.copyProperties(officialReply, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 关键词回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelOaReplyKeywordVo
     */
    @Override
    public ChannelOaReplyVo detail(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "数据不存在!");

        ChannelOaReplyVo vo = new ChannelOaReplyVo();
        BeanUtils.copyProperties(officialReply, vo);
        return vo;
    }

    /**
     * 关注回复新
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    @Override
    public void add(ChannelOaReplyParam channelOaReplyParam) {
        OfficialReply officialReply = new OfficialReply();
        officialReply.setReplyType(2);
        officialReply.setName(channelOaReplyParam.getName());
        officialReply.setContent(channelOaReplyParam.getContent());
        officialReply.setContentType(channelOaReplyParam.getContentType());
        officialReply.setStatus(channelOaReplyParam.getStatus());
        officialReply.setKeyword(channelOaReplyParam.getKeyword());
        officialReply.setMatchingType(channelOaReplyParam.getMatchingType());
        officialReply.setCreateTime(System.currentTimeMillis() / 1000);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.insert(officialReply);
    }

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param channelOaReplyParam 参数
     */
    @Transactional
    @Override
    public void edit(ChannelOaReplyParam channelOaReplyParam) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", channelOaReplyParam.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "关键词回复数据不存在!");

        officialReply.setReplyType(2);
        officialReply.setId(channelOaReplyParam.getId());
        officialReply.setName(channelOaReplyParam.getName());
        officialReply.setContent(channelOaReplyParam.getContent());
        officialReply.setContentType(channelOaReplyParam.getContentType());
        officialReply.setStatus(channelOaReplyParam.getStatus());
        officialReply.setKeyword(channelOaReplyParam.getKeyword());
        officialReply.setMatchingType(channelOaReplyParam.getMatchingType());
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

    /**
     * 关注回复删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "关键词回复数据不存在了!");

        officialReply.setIsDelete(1);
        officialReply.setDeleteTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

    /**
     * 默认回复状态
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void status(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("is_delete", 0)
                .eq("id", id)
                .last("limit 1"));

        Assert.notNull(officialReply, "关键词回复数据不存在!!");
        int status = officialReply.getStatus() == 1 ? 0 : 1;

        officialReply.setId(id);
        officialReply.setStatus(status);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

}
