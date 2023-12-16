package com.musinsa.core.common.model;

import com.musinsa.core.common.type.ResultCode;
import lombok.Getter;
import lombok.Setter;


@Getter
public class RestResponse<T> {
    private int httpStatus;
    private String message;
    private T data;

    public static RestResponse success() {
        RestResponse res = new RestResponse();
        res.httpStatus = ResultCode.OK.getHttpStatus().value();
        res.message = ResultCode.OK.getDefaultMessage();
        res.data = null;
        return res;
    }

    public static <T> RestResponse success(T data) {
        RestResponse res = new RestResponse();
        res.httpStatus = ResultCode.OK.getHttpStatus().value();
        res.message = ResultCode.OK.getDefaultMessage();
        res.data = data;
        return res;
    }

    public static <T> RestResponse fail(ResultCode resultCode) {
        RestResponse res = new RestResponse();
        res.httpStatus = resultCode.getHttpStatus().value();
        res.message = resultCode.getDefaultMessage();
        res.data = null;
        return res;
    }

    public static RestResponse fail(ResultCode resultCode, String msg) {
        RestResponse res = new RestResponse();
        res.httpStatus = resultCode.getHttpStatus().value();
        res.message = msg;
        res.data = null;
        return res;
    }

}
