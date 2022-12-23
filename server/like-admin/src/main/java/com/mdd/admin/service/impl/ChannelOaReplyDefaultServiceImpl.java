package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.IChannelOaReplyDefaultService;
import com.mdd.admin.validate.channel.ChannelRpDefaultValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpDefaultVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.OfficialReply;
import com.mdd.common.mapper.OfficialReplyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公众号默认回复服务实现类
 */
@Service
public class ChannelOaReplyDefaultServiceImpl implements IChannelOaReplyDefaultService {

    @Resource
    OfficialReplyMapper officialReplyMapper;

    /**
     * 默认回复列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<ChannelOaReplyVo>
     */
    @Override
    public PageResult<ChannelRpDefaultVo> list(PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        IPage<OfficialReply> iPage = officialReplyMapper.selectPage(new Page<>(pageNo, pageSize),
                new QueryWrapper<OfficialReply>()
                        .eq("reply_type", 3)
                        .eq("is_delete", 0)
                        .orderByDesc(Arrays.asList("sort", "id")));

        List<ChannelRpDefaultVo> list = new LinkedList<>();
        for (OfficialReply officialReply : iPage.getRecords()) {
            ChannelRpDefaultVo vo = new ChannelRpDefaultVo();
            BeanUtils.copyProperties(officialReply, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 默认回复详情
     *
     * @author fzr
     * @param id 主键
     * @return ChannelOaReplyDefaultVo
     */
    @Override
    public ChannelRpDefaultVo detail(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "默认数据不存在!");

        ChannelRpDefaultVo vo = new ChannelRpDefaultVo();
        BeanUtils.copyProperties(officialReply, vo);
        return vo;
    }

    /**
     * 默认回复新
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    @Override
    public void add(ChannelRpDefaultValidate defaultValidate) {
        if (defaultValidate.getStatus().equals(1)) {
            OfficialReply reply = new OfficialReply();
            reply.setStatus(0);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>().eq("reply_type", 3));
        }

        OfficialReply officialReply = new OfficialReply();
        officialReply.setReplyType(3);
        officialReply.setContent(defaultValidate.getContent());
        officialReply.setName(defaultValidate.getName());
        officialReply.setContentType(defaultValidate.getContentType());
        officialReply.setStatus(defaultValidate.getStatus());
        officialReply.setSort(defaultValidate.getSort());
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReply.setCreateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.insert(officialReply);
    }

    /**
     * 默认回复编辑
     *
     * @author fzr
     * @param defaultValidate 参数
     */
    @Transactional
    @Override
    public void edit(ChannelRpDefaultValidate defaultValidate) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", defaultValidate.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "默认回复数据不存在!");

        if (defaultValidate.getStatus().equals(1)) {
            OfficialReply reply = new OfficialReply();
            reply.setStatus(0);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>().eq("reply_type", officialReply.getReplyType()));
        }

        officialReply.setReplyType(3);
        officialReply.setId(defaultValidate.getId());
        officialReply.setName(defaultValidate.getName());
        officialReply.setContent(defaultValidate.getContent());
        officialReply.setContentType(defaultValidate.getContentType());
        officialReply.setStatus(defaultValidate.getStatus());
        officialReply.setSort(defaultValidate.getSort());
        officialReply.setCreateTime(System.currentTimeMillis() / 1000);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

    /**
     * 默认回复删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        OfficialReply officialReply =officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "默认回复数据不存在了!");

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

        Assert.notNull(officialReply, "默认回复数据不存在!");

        int status = officialReply.getStatus() == 1 ? 0 : 1;
        if (status == 1) {
            OfficialReply reply = new OfficialReply();
            reply.setStatus(0);
            reply.setUpdateTime(System.currentTimeMillis() / 1000);
            officialReplyMapper.update(reply, new QueryWrapper<OfficialReply>()
                    .eq("reply_type", officialReply.getReplyType()));
        }

        officialReply.setId(id);
        officialReply.setStatus(status);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.updateById(officialReply);
    }

}
