package com.mdd.front.service;

import com.mdd.front.vo.PcArticleDetailVo;

import java.util.Map;

public interface IPcService {

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> getConfig();

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @return Object
     */
    PcArticleDetailVo articleDetail(Integer id, Integer userId);

}
