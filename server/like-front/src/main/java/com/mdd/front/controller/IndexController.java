package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.service.IIndexService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Resource
    IIndexService iIndexService;

    /**
     * 首页
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> detail = iIndexService.index();
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
        Map<String, Object> detail = iIndexService.decorate(id);
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
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

    /**
     * 协议
     *
     * @author fzr
     * @param type 类型 service=服务协议,privacy=隐私协议
     * @return Object
     */
    @GetMapping("/policy")
    public Object policy(@RequestParam String type) {
        Map<String, String> map = iIndexService.policy(type);
        return AjaxResult.success(map);
    }

    /**
     * 热搜
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/search")
    public Object search() {
        List<String> list = iIndexService.search();
        return AjaxResult.success(list);
    }

}
