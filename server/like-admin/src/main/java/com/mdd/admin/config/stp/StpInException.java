package com.mdd.admin.config.stp;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Sa-Token的异常拦截
 */
@Slf4j
@ControllerAdvice
public class StpInException {

    /**
     * 拦截登录异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public AjaxResult<Object> handleNotLoginException(NotLoginException e){
        String msg = e.getMessage().startsWith("Token无效") ? "尚未登录,请先登录!" : e.getMessage();
        return AjaxResult.failed(ErrorEnum.TOKEN_INVALID.getCode(), msg);
    }

    /**
     * 拦截权限异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotPermissionException.class)
    @ResponseBody
    public AjaxResult<Object> handleNotPermissionException(){
        return AjaxResult.failed(ErrorEnum.NO_PERMISSION.getCode(), ErrorEnum.NO_PERMISSION.getMsg());
    }

}
