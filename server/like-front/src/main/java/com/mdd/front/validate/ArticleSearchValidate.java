package com.mdd.front.validate;

import com.mdd.common.validator.annotation.StringContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 文章搜索参数
 */
@Data
public class ArticleSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    @Length(max = 100, message = "关键词过长了")
    private String keyword;

    @StringContains(values = {"hot", "new"})
    private String sort;

}
