package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.IArticleService;
import com.mdd.front.vo.article.ArticleCateVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return  AjaxResult.success(list);
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        return AjaxResult.success();
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        return AjaxResult.success();
    }

}
