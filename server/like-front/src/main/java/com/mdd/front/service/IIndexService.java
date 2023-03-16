package com.mdd.front.service;

import com.mdd.common.core.PageResult;
import com.mdd.front.validate.common.PageValidate;
import com.mdd.front.vo.article.ArticleListedVo;

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
     * @param id 装修ID
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

}
