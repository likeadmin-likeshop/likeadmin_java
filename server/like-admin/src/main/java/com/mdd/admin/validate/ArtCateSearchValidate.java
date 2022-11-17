package com.mdd.admin.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类搜索参数
 */
@Data
public class ArtCateSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer isShow;

}
