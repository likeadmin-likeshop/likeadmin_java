package com.mdd.admin.validate.channel;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 渠道公众号回复管理参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChannelOaReplyParam extends BaseParam {

    public interface defaults{}
    public interface keywords{}
    public interface follow{}

    private Integer id;

    @NotNull(message = "name参数缺失", groups = {defaults.class, keywords.class, follow.class})
    @NotEmpty(message = "规则名称不能为空", groups = {defaults.class, keywords.class, follow.class})
    private String name;

    @NotNull(message = "content参数缺失", groups = {defaults.class, keywords.class, follow.class})
    @NotEmpty(message = "回复内容不能为空", groups = {defaults.class, keywords.class, follow.class})
    private String content;

    @NotNull(message = "contentType参数缺失", groups = {defaults.class, keywords.class, follow.class})
    @IntegerContains(values = {1, 2}, message = "contentType值不符合规范", groups = {defaults.class, keywords.class, follow.class})
    private Integer contentType;

    @NotNull(message = "status参数缺失", groups = {defaults.class, keywords.class, follow.class})
    @IntegerContains(values = {0, 1}, message = "状态选择异常", groups = {defaults.class, keywords.class, follow.class})
    private Integer status;

    @NotNull(message = "keyword参数缺失", groups = {keywords.class})
    @NotEmpty(message = "关键词不能为空", groups = {keywords.class})
    private String keyword;

    @NotNull(message = "matchingType参数缺失", groups = {keywords.class})
    @IntegerContains(values = {1, 2}, message = "匹配方式异常", groups = {defaults.class, keywords.class, follow.class})
    private Integer matchingType;

    @NotNull(message = "sort参数缺失", groups = {follow.class})
    private Integer sort;

}
