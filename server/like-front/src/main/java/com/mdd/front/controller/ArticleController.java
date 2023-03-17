package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.article.ArticleCollectValidate;
import com.mdd.front.validate.article.ArticleSearchValidate;
import com.mdd.front.validate.common.PageValidate;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/article")
@Api(tags = "文章管理")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    @NotLogin
    @GetMapping("/category")
    @ApiOperation(value="文章分类")
    public AjaxResult<List<ArticleCateVo>> category() {
        List<ArticleCateVo> list = iArticleService.category();
        return AjaxResult.success(list);
    }

    @NotLogin
    @GetMapping("/list")
    @ApiOperation(value="文章列表")
    public AjaxResult<PageResult<ArticleListedVo>> list(@Validated PageValidate pageValidate,
                                                        @Validated ArticleSearchValidate searchValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<ArticleListedVo> list = iArticleService.list(userId, pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @NotLogin
    @GetMapping("/detail")
    @ApiOperation(value="文章详情")
    public AjaxResult<ArticleDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        ArticleDetailVo vo = iArticleService.detail(id, userId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/collectList")
    @ApiOperation(value="收藏列表")
    public AjaxResult<PageResult<ArticleCollectVo>> collect(@Validated PageValidate pageValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        PageResult<ArticleCollectVo> list = iArticleService.collect(pageValidate, userId);
        return AjaxResult.success(list);
    }

    @PostMapping("/collectAdd")
    @ApiOperation(value="收藏加入")
    public AjaxResult<Object> addCollect(@Validated @RequestBody ArticleCollectValidate collectValidate) {
        Integer articleId = collectValidate.getArticleId();
        Integer userId = LikeFrontThreadLocal.getUserId();

        iArticleService.addCollect(articleId, userId);
        return AjaxResult.success();
    }

    @PostMapping("/collectCancel")
    @ApiOperation(value="收藏取消")
    public AjaxResult<Object> cancelCollect(@Validated @RequestBody ArticleCollectValidate collectValidate) {
        Integer articleId = collectValidate.getArticleId();
        Integer userId = LikeFrontThreadLocal.getUserId();

        iArticleService.cancelCollect(articleId, userId);
        return AjaxResult.success();
    }

}
