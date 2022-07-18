package com.hxkj.admin.service.article;

import com.hxkj.admin.validate.article.ArticleTextParam;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.vo.common.article.ArticleDetailVo;
import com.hxkj.admin.vo.common.article.ArticleListVo;
import com.hxkj.common.core.PageResult;

import java.util.Map;

/**
 * 文章服务接口类
 */
public interface IArticleTextService {

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    ArticleDetailVo detail(Integer id);

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleTextParam 文章参数
     */
    void add(ArticleTextParam articleTextParam);

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleTextParam 文章参数
     */
    void edit(ArticleTextParam articleTextParam);

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章主键
     */
    void del(Integer id);

}
