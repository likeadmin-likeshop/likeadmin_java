package com.hxkj.common.core;

import com.hxkj.common.enums.HttpEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class AjaxResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 状态码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 响应数据 **/
    private Object data;

    /**
     * 无参构造
     */
    public AjaxResult() {}

    /**
     * 带参构造
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     */
    public AjaxResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @return AjaxResult
     */
    public static AjaxResult success() {
        return new AjaxResult(HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult success(Integer code) {
        return new AjaxResult(code, HttpEnum.FAILED.getMsg(), new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult success(String msg) {
        return new AjaxResult(HttpEnum.SUCCESS.getCode(), msg, new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param data 响应数据
     * @return AjaxResult
     */
    public static AjaxResult success(Object data) {
        return new AjaxResult(HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult success(Integer code, String msg) {
        return new AjaxResult(code, msg, new ArrayList<>());
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 成功返回结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static AjaxResult success(Integer code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult failed(Integer code) {
        return new AjaxResult(code, HttpEnum.FAILED.getMsg(), new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult failed(String msg) {
        return new AjaxResult(HttpEnum.FAILED.getCode(), msg, new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param data 响应数据
     * @return AjaxResult
     */
    public static AjaxResult failed(Object data) {
        return new AjaxResult(HttpEnum.FAILED.getCode(), HttpEnum.FAILED.getMsg(), data);
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult failed(Integer code, String msg) {
        return new AjaxResult(code, msg, new ArrayList<>());
    }

    /**
     * 响应失败结果
     *
     * @author fzr
     * @param code 状态码
     * @param msg 提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static AjaxResult failed(Integer code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

}
