package com.mdd.admin.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 附件搜索参数
 */
@Data
public class AlbumSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    private Integer type;

    private String keyword;

}
