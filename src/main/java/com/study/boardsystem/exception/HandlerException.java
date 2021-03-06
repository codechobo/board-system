package com.study.boardsystem.exception;

import com.study.boardsystem.exception.code.CommonErrorCode;
import lombok.Getter;

/**
 * packageName    : com.study.boardsystem.exception
 * fileName       : HandlerException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
public class HandlerException extends RuntimeException {

    private final CommonErrorCode errorCode;

    public HandlerException(CommonErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public HandlerException(String message, CommonErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
