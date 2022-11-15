package com.mdd.admin.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.IArtCateService;
import com.mdd.admin.validate.ArtCateCreateValidate;
import com.mdd.admin.validate.ArtCateUpdateValidate;
import com.mdd.admin.validate.ArtCateSearchValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.article.ArticleCateVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类管理
 */
@RestController
@RequestMapping("api/article/cate")
public class ArtCateController {

    @Resource
    IArtCateService iArtCateService;

    /**
     * 分类所有
     *
     * @author fzr
     * @return AjaxResult<List<ArticleCateVo>>
     */
    @GetMapping("/all")
    public AjaxResult<List<ArticleCateVo>> all() {
        List<ArticleCateVo> list = iArtCateService.all();
        return AjaxResult.success(list);
    }

    /**
     * 分类列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return AjaxResult<PageResult<ArticleCateVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<ArticleCateVo>> list(@Validated PageValidate pageValidate,
                                                      @Validated ArtCateSearchValidate searchValidate) {
        PageResult<ArticleCateVo> list = iArtCateService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    /**
     * 分类详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<ArticleCateVo>
     */
    @GetMapping("/detail")
    public AjaxResult<ArticleCateVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        ArticleCateVo vo = iArtCateService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 分类新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章分类新增")
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody ArtCateCreateValidate createValidate) {
        iArtCateService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 分类编辑
     *
     * @author fzr
     * @param updateValidate 编辑
     * @return AjaxResult<Object>
     */
    @Log(title = "文章分类编辑")
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody ArtCateUpdateValidate updateValidate) {
        iArtCateService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 分类删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章分类删除")
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iArtCateService.del(idValidate.getId());
        return AjaxResult.success();
    }

    /**
     * 分类状态
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "文章分类状态")
    @PostMapping("/change")
    public AjaxResult<Object> change(@Validated @RequestBody IdValidate idValidate) {
        iArtCateService.change(idValidate.getId());
        return AjaxResult.success();
    }

}
