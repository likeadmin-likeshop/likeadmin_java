package com.hxkj.common.entity.server;

import lombok.Data;

import java.io.Serializable;

/**
 *  CPU相关信息
 */
@Data
public class Cpu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

}
