package com.hxkj.admin.controller;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.IArticleService;
import com.hxkj.admin.validate.article.CategoryParam;
import com.hxkj.admin.validate.article.ArticleParam;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.vo.article.ArticleDetailVo;
import com.hxkj.admin.vo.article.ArticleListVo;
import com.hxkj.admin.vo.article.CategoryVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    IArticleService iArticleService;

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/articleList")
    public Object articleList(@Validated PageParam pageParam,
                              @RequestParam Map<String, String> params) {
        PageResult<ArticleListVo> vos = iArticleService.articleList(pageParam, params);
        return AjaxResult.success(vos);
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @param id 文章ID
     * @return Object
     */
    @GetMapping("/articleDetail")
    public Object articleDetail(@Validated @IDMust() @RequestParam("id") Integer id) {
        try {
            ArticleDetailVo vo = iArticleService.articleDetail(id);
            return AjaxResult.success(vo);
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章新增")
    @PostMapping("/articleAdd")
    public Object articleAdd(
            @Validated(value = ArticleParam.create.class)
            @RequestBody ArticleParam articleParam) {
        try {
            iArticleService.articleAdd(articleParam);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章编辑")
    @PostMapping("/articleEdit")
    public Object articleEdit(@Validated(value = ArticleParam.update.class)
                                  @RequestBody ArticleParam articleParam) {
        try {
            iArticleService.articleEdit(articleParam);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 文章删除
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章删除")
    @PostMapping("/articleDel")
    public Object articleDel(@Validated(value = ArticleParam.delete.class)
                                @RequestBody ArticleParam articleParam) {
        try {
            iArticleService.articleDel(articleParam.getId());
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 分类列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/cateList")
    public Object cateList(@Validated PageParam pageParam,
                           @RequestParam Map<String, String> params) {
        try {
            PageResult<CategoryVo> voPageResult = iArticleService.cateList(pageParam, params);
            return AjaxResult.success(voPageResult);
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 分类详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/cateDetail")
    public Object cateDetail(@Validated @IDMust() @RequestParam("id") Integer id) {
        CategoryVo vo = iArticleService.cateDetail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 分类新增
     *
     * @author fzr
     * @param articleCateParam 分类参数
     * @return Object
     */
    @Log(title = "文章分类新增")
    @PostMapping("/cateAdd")
    public Object cateAdd(@Validated(value = CategoryParam.create.class)
                              @RequestBody CategoryParam articleCateParam) {
        try {
            iArticleService.cateAdd(articleCateParam);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 分类编辑
     *
     * @author fzr
     * @param articleCateParam 分类编辑
     * @return Object
     */
    @Log(title = "文章分类编辑")
    @PostMapping("/cateEdit")
    public Object cateEdit(@Validated(value = CategoryParam.update.class)
                               @RequestBody CategoryParam articleCateParam) {
        try {
            iArticleService.cateEdit(articleCateParam);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    /**
     * 分类删除
     *
     * @author fzr
     * @param articleCateParam 分类删除
     * @return Object
     */
    @Log(title = "文章分类删除")
    @PostMapping("/cateDel")
    public Object cateDel(@Validated(value = CategoryParam.delete.class)
                              @RequestBody CategoryParam articleCateParam) {
        try {
            iArticleService.cateDel(articleCateParam.getId());
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

}
