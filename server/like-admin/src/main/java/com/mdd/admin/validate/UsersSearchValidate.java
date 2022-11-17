package com.mdd.admin.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户搜索参数
 */
@Data
public class UsersSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer channel;

    private String keyword;

    private String startTime;

    private String endTime;

}
