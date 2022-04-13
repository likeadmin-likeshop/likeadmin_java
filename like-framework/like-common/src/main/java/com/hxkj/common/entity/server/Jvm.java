package com.hxkj.common.entity.server;

import lombok.Data;

import java.io.Serializable;

/**
 * JVM相关信息
 */
@Data
public class Jvm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JVM内存使用率
     */
    private double usage;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    /**
     * JDK名称
     */
    private String name;

    /**
     * 运行参数
     */
    private String inputArgs;

    /**
     * JDK运行时间
     */
    private String runTime;

    /**
     * JDK启动时间
     */
    private String startTime;
}
