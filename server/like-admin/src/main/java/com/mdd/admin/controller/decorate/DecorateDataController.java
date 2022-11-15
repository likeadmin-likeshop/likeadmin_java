package com.mdd.admin.controller.decorate;

import com.mdd.admin.service.IDecorateDataService;
import com.mdd.admin.vo.decorate.DecorateDataArticleVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 装修数据管理
 */
@RestController
@RequestMapping("api/decorate/data")
public class DecorateDataController {

    @Resource
    IDecorateDataService iDecorateDataService;

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return AjaxResult<List<DecorateDataArticleVo>>
     */
    @GetMapping("/article")
    public AjaxResult<List<DecorateDataArticleVo>> article(@RequestParam(defaultValue = "10") Integer limit) {
        List<DecorateDataArticleVo> list = iDecorateDataService.article(limit);
        return AjaxResult.success(list);
    }

}
