package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.IChannelOaReplyKeywordService;
import com.mdd.admin.validate.channel.ChannelRpKeywordValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.channel.ChannelRpKeywordVo;
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
     * @param pageValidate 分页参数
     * @return List<ChannelRpKeywordVo>
     */
    @Override
    public PageResult<ChannelRpKeywordVo> list(PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        IPage<OfficialReply> iPage = officialReplyMapper.selectPage(new Page<>(pageNo, pageSize),
                new QueryWrapper<OfficialReply>()
                        .eq("reply_type", 2)
                        .eq("is_delete", 0)
                        .orderByDesc(Arrays.asList("sort", "id")));

        List<ChannelRpKeywordVo> list = new LinkedList<>();
        for (OfficialReply officialReply : iPage.getRecords()) {
            ChannelRpKeywordVo vo = new ChannelRpKeywordVo();
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
     * @return ChannelRpKeywordVo
     */
    @Override
    public ChannelRpKeywordVo detail(Integer id) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "数据不存在!");

        ChannelRpKeywordVo vo = new ChannelRpKeywordVo();
        BeanUtils.copyProperties(officialReply, vo);
        return vo;
    }

    /**
     * 关注回复新
     *
     * @author fzr
     * @param keywordValidate 参数
     */
    @Override
    public void add(ChannelRpKeywordValidate keywordValidate) {
        OfficialReply officialReply = new OfficialReply();
        officialReply.setReplyType(2);
        officialReply.setName(keywordValidate.getName());
        officialReply.setContent(keywordValidate.getContent());
        officialReply.setContentType(keywordValidate.getContentType());
        officialReply.setStatus(keywordValidate.getStatus());
        officialReply.setKeyword(keywordValidate.getKeyword());
        officialReply.setSort(keywordValidate.getSort());
        officialReply.setMatchingType(keywordValidate.getMatchingType());
        officialReply.setCreateTime(System.currentTimeMillis() / 1000);
        officialReply.setUpdateTime(System.currentTimeMillis() / 1000);
        officialReplyMapper.insert(officialReply);
    }

    /**
     * 关注回复编辑
     *
     * @author fzr
     * @param keywordValidate 参数
     */
    @Transactional
    @Override
    public void edit(ChannelRpKeywordValidate keywordValidate) {
        OfficialReply officialReply = officialReplyMapper.selectOne(new QueryWrapper<OfficialReply>()
                .eq("id", keywordValidate.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(officialReply, "关键词回复数据不存在!");

        officialReply.setReplyType(2);
        officialReply.setId(keywordValidate.getId());
        officialReply.setName(keywordValidate.getName());
        officialReply.setContent(keywordValidate.getContent());
        officialReply.setContentType(keywordValidate.getContentType());
        officialReply.setStatus(keywordValidate.getStatus());
        officialReply.setKeyword(keywordValidate.getKeyword());
        officialReply.setMatchingType(keywordValidate.getMatchingType());
        officialReply.setSort(keywordValidate.getSort());
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
