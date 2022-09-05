package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.service.IIndexService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Resource
    IIndexService IIndexService;

    /**
     * 首页
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("scene", "101");
        params.put("mobile", "12323");
        params.put("params", new String[]{
                "code:203",
                "张三丰",
                "张无忌",
                "王二麻子",
                "张富贵"
        });

        (new NoticeDriver()).handle(params);


        Map<String, Object> detail = IIndexService.index();
        return AjaxResult.success(detail);
    }

    /**
     * 装修
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/decorate")
    public Object decorate(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> detail = IIndexService.decorate(id);
        return AjaxResult.success(detail);
    }

    /**
     * 配置
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/config")
    public Object config() {
        return AjaxResult.success();
    }
}
