package com.lvy.springboot.dto;


import java.io.Serializable;
import java.text.MessageFormat;

public class ResDto <T> implements Serializable {
    private static int PARAM_FAIL_CODE = 1002;


    private T data;
    private Integer code;
    private String msg;

    private static final long serialVersionUID = 1L;

    public ResDto() {
    }

    public ResDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResDto error() {
        return new ResDto(500, "系统繁忙,请稍后再试");
    }


    public static ResDto paramFail(String msg) {
        return new ResDto(PARAM_FAIL_CODE, msg);
    }


    public static ResDto success() {
        return new ResDto(200, "success");
    }

    public static ResDto failed() {
        return new ResDto(500,"failed");
    }

    public ResDto<T> success(T data) {
        return new ResDto<>(200, "success", data);
    }

    public ResDto nonAbsent(String msg) {
        return new ResDto<>(502, msg);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.code, this.msg);
    }
}
