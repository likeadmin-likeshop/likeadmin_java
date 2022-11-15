package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.IChannelOaReplyFollowService;
import com.mdd.admin.validate.channel.ChannelRpFollowsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpFollowsVo;
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
 * 公众号默认回复服务实现类
 */
@Service
public class ChannelOaReplyFollowServiceImpl implements IChannelOaReplyFollowService {

    @Resource
    OfficialReplyMapper officialReplyMapper;

    /**
     * 关注回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelRpFollowsVo>
     */
    @Override
    public PageResult<ChannelRpFollowsVo> list(PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        QueryWrapper<OfficialReply> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("reply_type", 1)
                .eq("is_delete", 0)
                .orderByDesc(Arrays.asList("sort", "id"));

        IPage<OfficialReply> iPage = officialReplyMapper.selectPage(new Page<>(pageNo, pageSize),
                new QueryWrapper<OfficialReply>()
                    .eq("reply_type", 1)
                    .eq("is_delete", 0)
                    .orderByDesc(Arrays.asList("sort", "id")));

        List<ChannelRpFollowsVo> list = new LinkedList<>();
        for (OfficialReply officialReply : iPage.getRecords()) {
            ChannelRpFollowsVo vo = new ChannelRpFollowsVo();
            BeanUtils.copyProperties(officialReply, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 关注回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelOaReplyDefaultVo
     */
    @Override
    public ChannelRpFollowsVo detail(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "关注回复数据不存在!");

        ChannelRpFollowsVo vo = new ChannelRpFollowsVo();
        BeanUtils.copyProperties(officialReply, vo);
        return vo;
    }

    /**
     * 关注回复新
     *
     * @author fzr
     * @param followsValidate 参数
     */
    @Override
    public void add(ChannelRpFollowsValidate followsValidate) {
        OfficialReply reply = new OfficialReply();

        if (followsValidate.getStatus().equals(1)) {
            reply.setStatus(1);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>().eq("reply_type", 1));
        }

        OfficialReply officialReply = new OfficialReply();
        officialReply.setReplyType(1);
        officialReply.setName(followsValidate.getName());
        officialReply.setContent(followsValidate.getContent());
        officialReply.setContentType(followsValidate.getContentType());
        officialReply.setStatus(followsValidate.getStatus());
        officialReply.setSort(followsValidate.getSort());
        officialReply.setCreateTime(System.currentTimeMillis() / 1000);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.insert(officialReply);
    }

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param followsValidate 参数
     */
    @Transactional
    @Override
    public void edit(ChannelRpFollowsValidate followsValidate) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", followsValidate.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "关注回复数据不存在!");

        if (followsValidate.getStatus().equals(1)) {
            OfficialReply reply = new OfficialReply();
            reply.setStatus(0);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>().eq("reply_type", officialReply.getReplyType()));
        }

        officialReply.setId(followsValidate.getId());
        officialReply.setReplyType(1);
        officialReply.setName(followsValidate.getName());
        officialReply.setContent(followsValidate.getContent());
        officialReply.setContentType(followsValidate.getContentType());
        officialReply.setStatus(followsValidate.getStatus());
        officialReply.setSort(followsValidate.getSort());
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

        Assert.notNull(officialReply, "关注回复数据不存在了!");

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

        Assert.notNull(officialReply, "关注回复数据不存在!!");

        int status = officialReply.getStatus() == 1 ? 0 : 1;
        if (status == 1) {
            OfficialReply reply = new OfficialReply();
            reply.setStatus(0);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>()
                    .eq("reply_type", officialReply.getReplyType()));
        }

        officialReply.setId(id);
        officialReply.setStatus(status);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

}
