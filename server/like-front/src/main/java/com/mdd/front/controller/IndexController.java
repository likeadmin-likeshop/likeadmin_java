package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.service.IIndexService;
import com.mdd.front.validate.PageValidate;
import com.mdd.front.vo.article.ArticleListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 主页管理
 */
@RestController
@RequestMapping("/api")
public class IndexController {

    @Resource
    IIndexService iIndexService;

    /**
     * 首页
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/index")
    public AjaxResult<Map<String, Object>> index() {
        Map<String, Object> detail = iIndexService.index();
        return AjaxResult.success(detail);
    }

    /**
     * 装修
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/decorate")
    public AjaxResult<Map<String, Object>> decorate(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> detail = iIndexService.decorate(id);
        return AjaxResult.success(detail);
    }

    /**
     * 配置
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/config")
    public AjaxResult<Map<String, Object>> config() {
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

    /**
     * 协议
     *
     * @author fzr
     * @param type 类型 service=服务协议,privacy=隐私协议
     * @return AjaxResult<Map<String, String>>
     */
    @GetMapping("/policy")
    public AjaxResult<Map<String, String>> policy(@RequestParam String type) {
        Map<String, String> map = iIndexService.policy(type);
        return AjaxResult.success(map);
    }

    /**
     * 热搜
     *
     * @author fzr
     * @return AjaxResult<List<String>>
     */
    @GetMapping("/hotSearch")
    public AjaxResult<List<String>> hotSearch() {
        List<String> list = iIndexService.hotSearch();
        return AjaxResult.success(list);
    }

    /**
     * 搜索
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜素参数
     * @return AjaxResult<PageResult<ArticleListVo>>
     */
    @GetMapping("/search")
    public AjaxResult<PageResult<ArticleListVo>> search(@Validated PageValidate pageValidate,
                                                        @RequestParam Map<String, String> params) {
        PageResult<ArticleListVo> list = iIndexService.search(pageValidate, params);
        return AjaxResult.success(list);
    }

}
