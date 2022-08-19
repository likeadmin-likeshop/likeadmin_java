package com.mdd.common.enums;

/**
 * 相册枚举
 */
public enum AlbumEnum {

    IMAGE(10, "图片"),
    Video(20, "视频");

    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    AlbumEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取状态码
     *
     * @author fzr
     * @return Long
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取提示
     *
     * @author fzr
     * @return String
     */
    public String getMsg() {
        return this.msg;
    }

}
