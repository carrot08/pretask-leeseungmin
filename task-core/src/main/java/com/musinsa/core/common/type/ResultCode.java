package com.musinsa.core.common.type;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;
public enum ResultCode {

    OK(HttpStatus.OK, "OK" ),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러입니다. 잠시 후 다시 시도해주세요." ),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다." ),
    BAD_REQUEST_DUPLICATE_BRAND_NAME(HttpStatus.BAD_REQUEST, "이미 존재하는 브랜드명입니다." ),

    NOT_FOUND_BRAND(HttpStatus.NOT_FOUND, "Not Found Brand" ),
    NOT_FOUND_BRAND_ITEM(HttpStatus.NOT_FOUND, "Not Found BrandItem" ),

    ;

    @Getter
    private final HttpStatus httpStatus;

    @Getter
    private final String defaultMessage;


    ResultCode(HttpStatus status, String message) {
        this.httpStatus = status;
        this.defaultMessage = message;
    }
}
