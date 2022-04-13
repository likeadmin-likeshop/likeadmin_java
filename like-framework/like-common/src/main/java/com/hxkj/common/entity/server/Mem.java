package com.hxkj.common.entity.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 內存相关信息
 */
@Data
public class Mem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    /**
     * 使用率
     */
    private double usage;

}
