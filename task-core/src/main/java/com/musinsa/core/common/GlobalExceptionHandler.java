package com.musinsa.core.common;

import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.common.model.RestResponse;
import com.musinsa.core.common.type.ResultCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectionRuntimeException.class)
    protected ResponseEntity<RestResponse> handleAdminRuntimeException(CollectionRuntimeException e) {
        return ResponseEntity.ok(RestResponse.fail(e.getCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<RestResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.ok(RestResponse.fail(ResultCode.UNEXPECTED_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<RestResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String msg = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity.ok(RestResponse.fail(ResultCode.BAD_REQUEST, msg));
    }

    @ExceptionHandler
    protected ResponseEntity<RestResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.ok(RestResponse.fail(ResultCode.BAD_REQUEST));
    }

    @ExceptionHandler
    protected ResponseEntity<RestResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.ok(RestResponse.fail(ResultCode.BAD_REQUEST));
    }

    @ExceptionHandler
    protected ResponseEntity<RestResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.ok(RestResponse.fail(ResultCode.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<RestResponse> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok(RestResponse.fail(ResultCode.UNEXPECTED_ERROR));
    }



}
