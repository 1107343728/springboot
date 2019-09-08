/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.lvy.springboot.exception;

import com.lvy.springboot.dto.ResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.StringJoiner;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static int DUPLICATE_KEY_CODE = 1001;
    private static int PARAM_FAIL_CODE = 1002;
    private static int VALIDATION_CODE = 1003;

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public ResDto handleRRException(BizException e) {
        logger.error(e.getMessage(), e);
        return new ResDto(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResDto handleBindException(BindException e) {
        logger.error(e.getMessage(), e);
        return new ResDto(PARAM_FAIL_CODE, e.getMessage());
    }


    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        // 按需重新封装需要返回的错误信息 解析原错误信息，封装后返回，此处返回非法的字段名称error.getField()，原始值error.getRejectedValue()，错误信息
        StringJoiner sj = new StringJoiner(";");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return new ResDto(PARAM_FAIL_CODE, sj.toString());
    }


    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public ResDto handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new ResDto(VALIDATION_CODE, e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResDto handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        StringJoiner sj = new StringJoiner(";");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessageTemplate()));
        return new ResDto(PARAM_FAIL_CODE, sj.toString());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResDto handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResDto(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return new ResDto(1005, "不支持'" + e.getMethod() + "'请求方法");
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public ResDto handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return new ResDto(DUPLICATE_KEY_CODE, "数据重复,请检查后提交");
    }


    @ExceptionHandler(Exception.class)
    public ResDto handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResDto(500, "系统繁忙,请稍后再试");
    }
}
