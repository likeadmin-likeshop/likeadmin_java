package com.hxkj.admin.controller.article;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.article.IArticleArchivesService;
import com.hxkj.admin.validate.article.ArticleTextParam;
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
@RestController(value = "articleArchive")
@RequestMapping("api/article/archive")
public class ArticleController {

    @Resource
    IArticleArchivesService iArticleArchivesService;

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
        PageResult<ArticleListVo> vos = iArticleArchivesService.list(pageParam, params);
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
        ArticleDetailVo vo = iArticleArchivesService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleTextParam 文章参数
     * @return Object
     */
    @Log(title = "文章新增")
    @PostMapping("/add")
    public Object add(
            @Validated(value = ArticleTextParam.create.class)
            @RequestBody ArticleTextParam articleTextParam) {
        iArticleArchivesService.add(articleTextParam);
        return AjaxResult.success();
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleTextParam 文章参数
     * @return Object
     */
    @Log(title = "文章编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = ArticleTextParam.update.class)
                       @RequestBody ArticleTextParam articleTextParam) {
        iArticleArchivesService.edit(articleTextParam);
        return AjaxResult.success();
    }

    /**
     * 文章删除
     *
     * @author fzr
     * @param articleTextParam 文章参数
     * @return Object
     */
    @Log(title = "文章删除")
    @PostMapping("/del")
    public Object del(@Validated(value = ArticleTextParam.delete.class)
                      @RequestBody ArticleTextParam articleTextParam) {
        iArticleArchivesService.del(articleTextParam.getId());
        return AjaxResult.success();
    }

}
