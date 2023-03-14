package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IPcService;
import com.mdd.front.vo.article.PcArticleCenterVo;
import com.mdd.front.vo.article.PcArticleDetailVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * pc端接口
 */
@RestController
@RequestMapping("/api/pc")
public class PcController {

    @Resource
    IPcService iPcService;

    /**
     * 主页
     *
     * @author cjh
     * @return AjaxResult<Map<String, Object>>
     */
    @NotLogin
    @GetMapping("/index")
    public AjaxResult<Map<String,Object>> index() {
        Map<String, Object> index = iPcService.index();
        return AjaxResult.success(index);
    }

    /**
     * 配置
     * @author cjh
     * @return AjaxResult<Map<String, Object>>
     */
    @NotLogin
    @GetMapping("/getConfig")
    public AjaxResult<Map<String, Object>> getConfig() {
        Map<String, Object> config = iPcService.getConfig();
        return AjaxResult.success(config);
    }

    /**
     * 资讯中心
     *
     * @author fzr
     * @return AjaxResult<List<PcArticleCenterVo>>
     */
    @NotLogin
    @GetMapping("/articleCenter")
    public AjaxResult<List<PcArticleCenterVo>> articleCenter() {
        List<PcArticleCenterVo> list = iPcService.articleCenter();
        return AjaxResult.success(list);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @return AjaxResult<PcArticleDetailVo>
     */
    @NotLogin
    @GetMapping("/articleDetail")
    public AjaxResult<PcArticleDetailVo> articleDetail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        PcArticleDetailVo vo = iPcService.articleDetail(id, userId);
        return AjaxResult.success(vo);
    }

}
