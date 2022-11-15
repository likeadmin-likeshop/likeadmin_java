package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.PageValidate;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章管理
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    /**
     * 文章分类
     *
     * @author fzr
     * @return AjaxResult<List<ArticleCateVo>>
     */
    @GetMapping("/category")
    public AjaxResult<List<ArticleCateVo>> category() {
        List<ArticleCateVo> list = iArticleService.category();
        return AjaxResult.success(list);
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @return AjaxResult<PageResult<ArticleListVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ArticleListVo>> list(@Validated PageValidate pageValidate,
                                                      @RequestParam(value = "cid", defaultValue = "0") Integer cid) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        PageResult<ArticleListVo> list = iArticleService.list(pageValidate, cid, userId);
        return AjaxResult.success(list);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @return AjaxResult<ArticleDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ArticleDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        ArticleDetailVo vo = iArticleService.detail(id, userId);
        return AjaxResult.success(vo);
    }

    /**
     * 文章收藏
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return AjaxResult<PageResult<ArticleCollectVo>>
     */
    @GetMapping("/collect")
    public AjaxResult<PageResult<ArticleCollectVo>> collect(@Validated PageValidate pageValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        PageResult<ArticleCollectVo> list = iArticleService.collect(pageValidate, userId);
        return AjaxResult.success(list);
    }

    /**
     * 加入收藏
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/addCollect")
    public AjaxResult<Object> addCollect(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("articleId"), "articleId参数缺失");
        Integer articleId = Integer.parseInt(params.get("articleId"));
        Integer userId = LikeFrontThreadLocal.getUserId();
        iArticleService.addCollect(articleId, userId);
        return AjaxResult.success();
    }

    /**
     * 取消收藏
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/cancelCollect")
    public AjaxResult<Object> cancelCollect(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("articleId"), "id参数缺失");
        Integer articleId = Integer.parseInt(params.get("articleId"));
        Integer userId = LikeFrontThreadLocal.getUserId();
        iArticleService.cancelCollect(articleId, userId);
        return AjaxResult.success();
    }

}
