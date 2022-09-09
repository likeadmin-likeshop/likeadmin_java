package com.mdd.front.service;

import com.mdd.common.core.PageResult;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleListVo;

import java.util.List;
import java.util.Map;

/**
 * 首页服务接口类
 */
public interface IIndexService {

    /**
     * 首页
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 装修
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> decorate(Integer id);

    /**
     * 配置
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> config();

    /**
     * 政策
     *
     * @author fzr
     * @param type 类型 service=服务协议,privacy=隐私协议
     * @return Map<String, Object>
     */
    Map<String, String> policy(String type);

    /**
     * 热搜
     *
     * @author fzr
     * @return List<String>
     */
    List<String> hotSearch();

    /**
     * 搜索
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> search(PageParam pageParam, Map<String, String> params);
}
