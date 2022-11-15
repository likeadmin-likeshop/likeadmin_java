package com.mdd.admin.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新参数
 */
@Data
public class UsersUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String field;

    private String value;

}
