package com.mdd.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CrontabDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String command;
    private String rules;
    private Integer status;

}
