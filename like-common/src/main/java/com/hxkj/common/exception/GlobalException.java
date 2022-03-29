package com.hxkj.common.exception;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.enums.HttpEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

//    /**
//     * 处理所有不可知异常
//     *
//     * @param e 异常
//     * @return AjaxResult
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(Throwable.class)
//    public AjaxResult handleException(Throwable e) {
//        log.error(ThrowableUtil.getStackTrace(e));
//        return AjaxResult.failed(HttpEnum.SYSTEM_ERROR.getCode(), HttpEnum.SUCCESS.getMsg());
//    }

    /**
     * 拦截表单参数校验FROM
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResult handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        Integer code = HttpEnum.PARAMS_VALID_ERROR.getCode();
        String msg   = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return AjaxResult.failed(code, msg);
    }

    /**
     * 拦截路径参数校验PATH
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public AjaxResult handlePathException(MissingServletRequestParameterException e) {
        Integer code = HttpEnum.PARAMS_VALID_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg);
    }

    /**
     * 拦截JSON参数校验
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Integer code = HttpEnum.PARAMS_VALID_ERROR.getCode();
        String msg   = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return AjaxResult.failed(code, msg);
    }

    /**
     * 拦截参数类型不正确
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Integer code = HttpEnum.PARAMS_TYPE_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg.split(";")[0]);
    }

    /**
     * 拦截请求方法
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public AjaxResult handleRequestMethodException(HttpRequestMethodNotSupportedException e) {
        Integer code = HttpEnum.REQUEST_METHOD_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg);
    }

    /**
     * 拦截断言异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public AjaxResult handleIllegalArgumentException(IllegalArgumentException e) {
        Integer code = HttpEnum.ASSERT_ARGUMENT_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg);
    }

    /**
     * 拦截MybatisPlus异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MybatisPlusException.class)
    @ResponseBody
    public AjaxResult handleMybatisPlusException(MybatisPlusException e) {
        Integer code = HttpEnum.ASSERT_MYBATIS_ERROR.getCode();
        String msg   = Objects.requireNonNull(e.getMessage());
        return AjaxResult.failed(code, msg);
    }

}
