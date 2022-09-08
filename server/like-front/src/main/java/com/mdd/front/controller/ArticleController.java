package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.PageParam;
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
        Integer userId = LikeFrontThreadLocal.getUserId();
        PageResult<ArticleListVo> list = iArticleService.list(pageParam, cid, userId);
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
        Integer userId = LikeFrontThreadLocal.getUserId();
        ArticleDetailVo vo = iArticleService.detail(id, userId);
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
        Integer userId = LikeFrontThreadLocal.getUserId();
        System.out.println(userId);
        PageResult<ArticleCollectVo> list = iArticleService.collect(pageParam, userId);
        return AjaxResult.success(list);
    }

    /**
     * 加入收藏
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/addCollect")
    public Object addCollect(@RequestBody Map<String, String> params) {
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
     * @return Object
     */
    @PostMapping("/cancelCollect")
    public Object cancelCollect(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("id"), "id参数缺失");
        Integer id = Integer.parseInt(params.get("id"));
        Integer userId = LikeFrontThreadLocal.getUserId();
        iArticleService.cancelCollect(id, userId);
        return AjaxResult.success();
    }

}
