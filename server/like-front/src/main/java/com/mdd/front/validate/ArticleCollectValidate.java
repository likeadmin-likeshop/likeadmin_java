package com.mdd.front.validate;

import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章收藏参数
 */
@Data
public class ArticleCollectValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "articleId参数缺失")
    @IDMust(message = "articleId参数必须大于0")
    private Integer articleId;

}
