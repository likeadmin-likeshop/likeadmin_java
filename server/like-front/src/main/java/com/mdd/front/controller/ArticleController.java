package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    /**
     * 文章分类
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/category")
    public Object category() {
        List<ArticleCateVo> list = iArticleService.category();
        return AjaxResult.success(list);
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                      @RequestParam(value = "cid", defaultValue = "0") Integer cid) {
        PageResult<ArticleListVo> list = iArticleService.list(pageParam, cid);
        return AjaxResult.success(list);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleDetailVo vo = iArticleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 文章收藏
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return Object
     */
    @GetMapping("/collect")
    public Object collect(@Validated PageParam pageParam) {
        PageResult<ArticleCollectVo> list = iArticleService.collect(pageParam);
        return AjaxResult.success(list);
    }
}
