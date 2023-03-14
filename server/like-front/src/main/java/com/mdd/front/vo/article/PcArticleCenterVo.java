package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 资讯中心数据
 */
@Data
public class PcArticleCenterVo implements Serializable {

    private Integer id;
    private String name;
    private Object article;

}
