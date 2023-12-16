package com.musinsa.core.common.exception;

import com.musinsa.core.common.type.ResultCode;
import lombok.Getter;

@Getter
public class CollectionRuntimeException extends RuntimeException {

    private ResultCode code;
    private String message;

    public CollectionRuntimeException(ResultCode code) {
        super();
        this.code = code;
        this.message = code.getDefaultMessage();
    }
    public CollectionRuntimeException(ResultCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public CollectionRuntimeException(ResultCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public CollectionRuntimeException(ResultCode code, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = code.getDefaultMessage();
    }
}
