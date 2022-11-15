package com.mdd.admin.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.IArticleService;
import com.mdd.admin.validate.ArticleCreateValidate;
import com.mdd.admin.validate.ArticleSearchValidate;
import com.mdd.admin.validate.ArticleUpdateValidate;

import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.article.ArticleDetailVo;
import com.mdd.admin.vo.article.ArticleListedVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章管理
 */
@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return AjaxResult<PageResult<ArticleListVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ArticleListedVo>> list(@Validated PageValidate pageValidate,
                                                        @Validated ArticleSearchValidate searchValidate) {
        PageResult<ArticleListedVo> vos = iArticleService.list(pageValidate, searchValidate);
        return AjaxResult.success(vos);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章ID
     * @return AjaxResult<ArticleDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ArticleDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleDetailVo vo = iArticleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 文章新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章新增")
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody ArticleCreateValidate createValidate) {
        iArticleService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章编辑")
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody ArticleUpdateValidate updateValidate) {
        iArticleService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 文章删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章删除")
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iArticleService.del(idValidate.getId());
        return AjaxResult.success();
    }

    /**
     * 文章状态
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章状态")
    @PostMapping("/change")
    public AjaxResult<Object> change(@Validated @RequestBody IdValidate idValidate) {
        iArticleService.change(idValidate.getId());
        return AjaxResult.success();
    }

}
