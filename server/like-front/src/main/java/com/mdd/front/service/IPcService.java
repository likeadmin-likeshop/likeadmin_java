package com.mdd.front.service;

import com.mdd.front.vo.article.PcArticleCenterVo;
import com.mdd.front.vo.article.PcArticleDetailVo;

import java.util.List;
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
     * 资讯中心
     *
     * @author fzr
     * @return PcArticleCenterVo
     */
    List<PcArticleCenterVo> articleCenter();

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章ID
     * @param userId 用户ID
     * @return PcArticleDetailVo
     */
    PcArticleDetailVo articleDetail(Integer id, Integer userId);

}
