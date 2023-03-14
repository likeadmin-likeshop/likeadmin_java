package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.enums.NoticeEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.notice.NoticeRecordMapper;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.plugin.notice.vo.NoticeSmsVo;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.ToolUtils;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.front.service.IIndexService;
import com.mdd.front.validate.common.PageValidate;
import com.mdd.front.validate.common.SmsValidate;
import com.mdd.front.vo.article.ArticleListedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/index")
@Api(tags = "主页管理")
public class IndexController {

    @Resource
    NoticeRecordMapper noticeRecordMapper;

    @Resource
    IIndexService iIndexService;

    /**
     * 首页
     *
     * @author fzr
     * @return AjaxResult<Map<String, Object>>
     */
    @NotLogin
    @GetMapping("/index")
    @ApiOperation(value="首页数据")
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
    @NotLogin
    @GetMapping("/decorate")
    @ApiOperation(value="装修数据")
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
    @NotLogin
    @GetMapping("/config")
    @ApiOperation(value="公共配置")
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
    @NotLogin
    @GetMapping("/policy")
    @ApiOperation(value="政策协议")
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
    @NotLogin
    @GetMapping("/hotSearch")
    @ApiOperation(value="热门搜索")
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
    @NotLogin
    @GetMapping("/search")
    @ApiOperation(value="搜索文章")
    public AjaxResult<PageResult<ArticleListedVo>> search(@Validated PageValidate pageValidate,
                                                          @RequestParam Map<String, String> params) {
        PageResult<ArticleListedVo> list = iIndexService.search(pageValidate, params);
        return AjaxResult.success(list);
    }

    /**
     * 发送短信
     *
     * @author fzr
     * @param smsValidate 参数
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/sendSms")
    @ApiOperation(value="发送短信")
    public AjaxResult<Object> sendSms(@Validated @RequestBody SmsValidate smsValidate) {
        NoticeRecord noticeRecord = noticeRecordMapper.selectOne(new QueryWrapper<NoticeRecord>()
                .eq("account", smsValidate.getMobile())
                .eq("scene", smsValidate.getScene())
                .eq("status", Arrays.asList(NoticeEnum.STATUS_WAIT, NoticeEnum.STATUS_OK))
                .orderByDesc("id")
                .last("limit 1"));

        if (StringUtils.isNotNull(noticeRecord)) {
            if (noticeRecord.getCreateTime() >= (System.currentTimeMillis() / 1000 - 60)){
                throw new OperateException("操作频繁,请稍后再试!");
            }
        }

        NoticeSmsVo params = new NoticeSmsVo()
                .setScene(smsValidate.getScene())
                .setMobile(smsValidate.getMobile())
                .setExpire(900)
                .setParams(new String[] {
                        "code:" + ToolUtils.randomInt(4)
                });

        NoticeDriver.handle(params);
        return AjaxResult.success();
    }

}
