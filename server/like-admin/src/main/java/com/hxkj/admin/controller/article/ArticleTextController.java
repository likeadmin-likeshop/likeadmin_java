package com.hxkj.admin.controller.article;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.article.IArticleTextService;
import com.hxkj.admin.validate.article.ArticleParam;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.vo.common.article.ArticleDetailVo;
import com.hxkj.admin.vo.common.article.ArticleListVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文章管理
 */
@RestController
@RequestMapping("api/article")
public class ArticleTextController {

    @Resource
    IArticleTextService iArticleTextService;

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object articleList(@Validated PageParam pageParam,
                              @RequestParam Map<String, String> params) {
        PageResult<ArticleListVo> vos = iArticleTextService.list(pageParam, params);
        return AjaxResult.success(vos);
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @param id 文章ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleDetailVo vo = iArticleTextService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章新增")
    @PostMapping("/add")
    public Object add(
            @Validated(value = ArticleParam.create.class)
            @RequestBody ArticleParam articleParam) {
        iArticleTextService.add(articleParam);
        return AjaxResult.success();
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = ArticleParam.update.class)
                       @RequestBody ArticleParam articleParam) {
        iArticleTextService.edit(articleParam);
        return AjaxResult.success();
    }

    /**
     * 文章删除
     *
     * @author fzr
     * @param articleParam 文章参数
     * @return Object
     */
    @Log(title = "文章删除")
    @PostMapping("/del")
    public Object del(@Validated(value = ArticleParam.delete.class)
                      @RequestBody ArticleParam articleParam) {
        iArticleTextService.del(articleParam.getId());
        return AjaxResult.success();
    }

}
