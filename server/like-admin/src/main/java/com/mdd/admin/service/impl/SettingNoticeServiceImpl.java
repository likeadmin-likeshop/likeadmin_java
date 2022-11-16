package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.ISettingNoticeService;
import com.mdd.admin.vo.setting.SettingNoticeDetailVo;
import com.mdd.admin.vo.setting.SettingNoticeListedVo;
import com.mdd.common.entity.notice.NoticeSetting;
import com.mdd.common.mapper.notice.NoticeSettingMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.ToolsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 通知设置服务实现类
 */
@Service
public class SettingNoticeServiceImpl implements ISettingNoticeService {

    @Resource
    NoticeSettingMapper noticeSettingMapper;

    /**
     * 通知设置列表
     *
     * @author fzr
     * @param recipient 1=用户, 2=平台
     * @return List<NoticeSettingListVo>
     */
    @Override
    public List<SettingNoticeListedVo> list(Integer recipient) {
        QueryWrapper<NoticeSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recipient", recipient);
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByAsc("id");

        List<NoticeSetting> noticeSettings = noticeSettingMapper.selectList(queryWrapper);
        List<SettingNoticeListedVo> list = new LinkedList<>();
        for (NoticeSetting n : noticeSettings) {
            SettingNoticeListedVo vo = new SettingNoticeListedVo();
            BeanUtils.copyProperties(n, vo);

            Map<String, String> systemMap = ToolsUtil.jsonToMap(n.getSystemNotice());
            Map<String, String> smsMap = ToolsUtil.jsonToMap(n.getSmsNotice());
            Map<String, String> oaMap = ToolsUtil.jsonToMap(n.getOaNotice());
            Map<String, String> mnpMap = ToolsUtil.jsonToMap(n.getMnpNotice());

            vo.setType(n.getType()==1?"业务通知":"验证码");
            vo.setSystemStatus(Integer.parseInt(systemMap.getOrDefault("status", "0")));
            vo.setSmsStatus(Integer.parseInt(smsMap.getOrDefault("status", "0")));
            vo.setOaStatus(Integer.parseInt(oaMap.getOrDefault("status", "0")));
            vo.setMnpStatus(Integer.parseInt(mnpMap.getOrDefault("status", "0")));
            vo.setCreateTime(TimeUtil.timestampToDate(n.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(n.getUpdateTime()));
            list.add(vo);
        }

        return list;
    }

    /**
     * 通知设置详情
     *
     * @author fzr
     * @param id 主键
     * @return NoticeSettingDetailVo
     *
     */
    @Override
    public SettingNoticeDetailVo detail(Integer id) {
        NoticeSetting noticeSetting = noticeSettingMapper.selectOne(new QueryWrapper<NoticeSetting>()
                .select(NoticeSetting.class, info ->
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time") &&
                        !info.getColumn().equals("create_time") &&
                        !info.getColumn().equals("update_time")
                )
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Map<String, Object> systemMap = ToolsUtil.jsonToMapAsObj(noticeSetting.getSystemNotice());
        Map<String, Object> oaMap  = ToolsUtil.jsonToMapAsObj(noticeSetting.getOaNotice());
        Map<String, Object> mnpMap = ToolsUtil.jsonToMapAsObj(noticeSetting.getMnpNotice());
        Map<String, Object> smsMap = ToolsUtil.jsonToMapAsObj(noticeSetting.getSmsNotice());

        SettingNoticeDetailVo vo = new SettingNoticeDetailVo();
        BeanUtils.copyProperties(noticeSetting, vo);
        vo.setType(noticeSetting.getType()==1?"业务通知":"验证码");
        vo.setSystemNotice(systemMap);
        vo.setOaNotice(oaMap);
        vo.setMnpNotice(mnpMap);
        vo.setSmsNotice(smsMap);
        return vo;
    }

    /**
     * 通知设置保存
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, Object> params) {
        Integer id = Integer.parseInt(params.get("id").toString());
        NoticeSetting noticeSetting = noticeSettingMapper.selectOne(new QueryWrapper<NoticeSetting>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Map<String, String> systemParam = ToolsUtil.objectToMap(params.get("systemNotice"));
        Map<String, String> systemMap = ToolsUtil.jsonToMap(noticeSetting.getSystemNotice());
        systemMap.put("title", systemParam.get("title"));
        systemMap.put("content", systemParam.get("content"));
        systemMap.put("status", systemParam.get("status"));

        Map<String, String> smsParam = ToolsUtil.objectToMap(params.get("smsNotice"));
        Map<String, String> smsMap = ToolsUtil.jsonToMap(noticeSetting.getSmsNotice());
        smsMap.put("templateId", smsParam.get("templateId"));
        smsMap.put("content", smsParam.get("content"));
        smsMap.put("status", smsParam.get("status"));

        Map<String, String> oaParam = ToolsUtil.objectToMap(params.get("oaNotice"));
        Map<String, String> oaMap = ToolsUtil.jsonToMap(noticeSetting.getOaNotice());
        oaMap.put("name", oaParam.getOrDefault("name", ""));
        oaMap.put("first", oaParam.get("first"));
        oaMap.put("remark", oaParam.get("remark"));
        oaMap.put("templateId", oaParam.get("templateId"));
        oaMap.put("templateSn", oaParam.get("templateSn"));
        oaMap.put("tpl", JSON.toJSONString(oaParam.get("tpl")));
        oaMap.put("status", oaParam.get("status"));

        Map<String, String> mnpParam = ToolsUtil.objectToMap(params.get("mnpNotice"));
        Map<String, String> mnpMap = ToolsUtil.jsonToMap(noticeSetting.getMnpNotice());
        mnpMap.put("templateId", mnpParam.get("templateId"));
        mnpMap.put("templateSn", mnpParam.get("templateSn"));
        mnpMap.put("tpl", mnpParam.get("tpl"));
        mnpMap.put("status", mnpParam.get("status"));

        noticeSetting.setSystemNotice(JSON.toJSONString(systemMap));
        noticeSetting.setSmsNotice(JSON.toJSONString(smsMap));
        noticeSetting.setOaNotice(JSON.toJSONString(oaMap));
        noticeSetting.setMnpNotice(JSON.toJSONString(mnpMap));
        noticeSetting.setUpdateTime(System.currentTimeMillis() / 1000);
        noticeSettingMapper.updateById(noticeSetting);
    }

}
